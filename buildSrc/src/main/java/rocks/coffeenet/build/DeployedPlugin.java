package rocks.coffeenet.build;

import de.marcphilipp.gradle.nexus.NexusPublishExtension;
import de.marcphilipp.gradle.nexus.NexusPublishPlugin;

import io.codearte.gradle.nexus.NexusStagingExtension;
import io.codearte.gradle.nexus.NexusStagingPlugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlatformPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.publish.Publication;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;

import org.gradle.plugins.signing.SigningExtension;
import org.gradle.plugins.signing.SigningPlugin;

import java.util.Optional;


/**
 * A plugin applied to a project that should be deployed.
 *
 * <p>It in general creates the necessary publications for Maven based publication. Additionally it can configure
 * SonaType OSS repository and artifact signing, so we publish to Maven Central.</p>
 *
 * @author  Andy Wilkinson
 * @author  Florian 'punycode' Krupicka - zh@punyco.de
 * @see  <a
 *       href="https://github.com/spring-projects/spring-boot/blob/master/buildSrc/src/main/java/org/springframework/boot/build/DeployedPlugin.java">
 *       Spring Boot</a>
 */
public class DeployedPlugin implements Plugin<Project> {

    /**
     * Property to determine if to apply publication to SonaType OSS repository.
     */
    public static final String SONATYPE_OSS_PROPERTY_NAME = "sonatype";

    /**
     * Project property to determine whether to sign the deployed artifacts with GPG. This is automatically enabled
     * when also publishing to SonaType OSS repository.
     */
    public static final String SIGN_GPG_PROPERTY_NAME = "signGpg";

    @Override
    public void apply(Project project) {

        project.getPlugins().apply(MavenPublishPlugin.class);

        // Create a new Maven publication
        PublishingExtension publishing = project.getExtensions().getByType(PublishingExtension.class);
        MavenPublication mavenPublication = publishing.getPublications().create("maven", MavenPublication.class);
        mavenPublication.suppressAllPomMetadataWarnings();

        // Publish all Java artifacts and the platform BOMs

        //J-
        //@formatter:off
        project.getPlugins().withType(JavaPlugin.class).all((plugin) ->
            project.getComponents().matching((component) -> component.getName().equals("java"))
                .all(mavenPublication::from));
        project.getPlugins().withType(JavaPlatformPlugin.class).all((plugin) ->
            project.getComponents().matching((component) -> component.getName().equals("javaPlatform"))
                .all(mavenPublication::from));


        Optional<String> githubToken = nonEmptyEnvironment("GITHUB_TOKEN");
        Optional<String> githubUsername = nonEmptyEnvironment("GITHUB_USERNAME");
        Optional<String> githubPassword = nonEmptyEnvironment("GITHUB_PASSWORD");

        if (githubToken.isPresent() || (githubUsername.isPresent() && githubPassword.isPresent())) {
            String username = githubUsername.orElse("coffeenet");
            String token = githubToken.orElseGet(githubPassword::get);

            applyGithubPackagesPublishing(project, username, token);
        }

        if (project.hasProperty(SONATYPE_OSS_PROPERTY_NAME)) {
            applySonaTypeOssPublishing(project);
        }

        if (project.hasProperty(SIGN_GPG_PROPERTY_NAME) || project.hasProperty(SONATYPE_OSS_PROPERTY_NAME)) {
            applyGpgSigning(project, mavenPublication);
        }
        //@formatter:on
        //J+
    }


    private void applyGithubPackagesPublishing(Project project, String username, String password) {

        //J-
        //@formatter:off
        project.getExtensions().configure(PublishingExtension.class, (publishing) -> {
            publishing.getRepositories().maven(maven -> {
                maven.setName("GitHubPackages");
                maven.setUrl("https://maven.pkg.github.com/coffeenet/coffeenet-starter");
                maven.credentials(credentials -> {
                    credentials.setUsername(username);
                    credentials.setPassword(password);
                });
            });
        });
        //@formatter:on
        //J+
    }


    private void applyGpgSigning(Project project, Publication publication) {

        project.getPlugins().apply(SigningPlugin.class);

        SigningExtension signing = project.getExtensions().getByType(SigningExtension.class);
        signing.sign(publication);

        // Support signing from in-memory keys via environment variables
        Optional<String> privateKey = nonEmptyEnvironment("GPG_PRIVATE_KEY");
        Optional<String> passphrase = nonEmptyEnvironment("GPG_PASSPHRASE");

        if (privateKey.isPresent() && passphrase.isPresent()) {
            signing.useInMemoryPgpKeys(privateKey.get(), passphrase.get());
        }
    }


    /**
     * Apply the publishing extensions for SonaType OSS repository.
     */
    private void applySonaTypeOssPublishing(Project project) {

        Project rootProject = project.getRootProject();

        if (!project.getRootProject().getPlugins().hasPlugin(NexusStagingPlugin.class)) {
            rootProject.getPlugins().apply(NexusStagingPlugin.class);

            Optional<String> sonatypeUsername = nonEmptyEnvironment("SONATYPE_USERNAME");
            Optional<String> sonatypePassword = nonEmptyEnvironment("SONATYPE_PASSWORD");

            if (sonatypeUsername.isPresent() && sonatypePassword.isPresent()) {
                NexusStagingExtension staging = rootProject.getExtensions().getByType(NexusStagingExtension.class);
                staging.setUsername(sonatypeUsername.get());
                staging.setPassword(sonatypePassword.get());
            }
        }

        project.getPlugins().apply(NexusPublishPlugin.class);
        project.getExtensions().getByType(NexusPublishExtension.class).getRepositories().sonatype();
    }


    // Helpers
    private static Optional<String> nonEmptyEnvironment(String key) {

        String value = System.getenv(key);

        return value != null ? "".equals(value.trim()) ? Optional.empty() : Optional.of(value) : Optional.empty();
    }
}
