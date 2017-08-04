package coffee.synyx.autoconfigure.navigation;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


/**
 * Properties for the navigation starters.
 *
 * @author  Tobias Schneider - schneider@synyx.de
 * @since  0.15.0
 */
@Validated
@ConfigurationProperties("coffeenet.navigation")
public class CoffeeNetNavigationProperties {

    @NotEmpty(message = "Please provide the name of the profile application")
    private String profileServiceName = "profile";

    @NotEmpty(message = "Please provide the path to the logout functionality or stay forever in the CoffeeNet")
    private String logoutPath = "/logout";

    @NotNull(message = "Please provide the information to show or not show the version number in the navigation")
    private boolean displayVersions = true;

    public String getProfileServiceName() {

        return profileServiceName;
    }


    public void setProfileServiceName(String profileServiceName) {

        this.profileServiceName = profileServiceName;
    }


    public String getLogoutPath() {

        return logoutPath;
    }


    public void setLogoutPath(String logoutPath) {

        this.logoutPath = logoutPath;
    }


    public boolean isDisplayVersions() {

        return displayVersions;
    }


    public void setDisplayVersions(boolean displayVersions) {

        this.displayVersions = displayVersions;
    }


    @Override
    public String toString() {

        return "CoffeeNetNavigationProperties{"
            + "profileServiceName='" + profileServiceName + '\''
            + ", logoutPath='" + logoutPath + '\''
            + ", displayVersions=" + displayVersions + '}';
    }
}
