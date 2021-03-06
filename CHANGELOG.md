# Changelog 
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.39.0]
### Changed
- Move from Maven to Gradle based build
  [\#70](https://github.com/coffeenet/coffeenet-starter/pull/70)
  - Build logic is structured in `buildSrc` according to Gradle recommendations
  - Only relevant dependencies are published now
- Move build and release pipeline from Travis CI to GitHub actions
  [\#58](https://github.com/coffeenet/coffeenet-starter/issues/58),
  [\#63](https://github.com/coffeenet/coffeenet-starter/issues/63)
- Restructure of modules (see root [README.md](./README.md]))
  - Add `coffeenet-starter-dependencies` as dependency BOM
  - `autoconfigure` is now `coffeenet-autoconfigure`
  - `starter-discovery` is now `coffeenet-starter-discovery`
  - `starter-logging` is now `coffeenet-starter-logging`
  - `starter-monitoring` is now `coffeenet-starter-monitoring`
  - `starter-navigation` is now `coffeenet-starter-navigation`
  - `starter-navigation-javascript` is now `coffeenet-starter-navigation-javascript`
  - `starter-navigation-thymeleaf` is now `coffeenet-starter-navigation-thymeleaf`
  - `starter-security` is now `coffeenet-starter-security`
- `.editorconfig` for JavaScript files

### Removed
- `maven-release-plugin`
- Uploading release artifacts to GitHub

### Fixed
- Do not leak distributionManagement information into the CoffeeNet Applications
- Use CoffeeNet named @ConditionalOnMissingBean condition instead of generic

## [0.38.2]
### Added
- Javadoc, Source and Compiler plugin
  to `coffeenet.actuator` package


## [0.38.1]
### Fixed
- Deployment pipeline to maven central


## [0.38.0]
### Added
- 'coffeenet.navigation.display-in-navigation-for-roles'
   to display navigation element of an application in the navigation
   for special roles and hide it for all others
- sonarcloud analyse

### Changed
- GroupId from 'coffee.synyx' to 'rocks.coffeenet'
- Dependency
  - Upgrade `rocks.coffeenet:navigation-bar` to 0.16.0

### Deprecated
- 'coffeenet.allowed-authorities' in favor of 'coffeenet.navigation.display-in-navigation-for-roles'


## [0.37.0]
### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.19`


## [0.36.0]
### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.17`


## [0.35.0]
### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.16`


## [0.34.0]
### Added
- Open `/health` and `/info` endpoints for all application by default


## [0.33.1]
### Fixed
- Username in navigation bar correct font color
  if profile service is not available


## [0.33.0]
### Added
- `coffeenet-starter-monitoring`
- `coffeenet-actuator`

### Changed
- Upgrade navigation bar to 0.15.0
- New navigation design


## [0.32.0]
### Added
- Automatic upload of the relevant part of the changelog on release

### Changed
- Dependency
  - Upgrade `logback-gelf-appender` to `1.5`
  - Upgrade `spring boot` to `1.5.15`
- Use keepachangelog format for CHANGELOG.md


## [0.31.0]
### Added
- Case insensitive ordering of apps in the navigation bar

### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.14`


## [0.30.0]
### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.13`
  
### Fixed
- Always add `OAuth2ClientContextFilter` if security starter is in use


## [0.29.0]
### Changed
- Dependency
  - Upgrade `logback-gelf-appender` to `1.4`
  - Add jacoco and coveralls for test coverage
  - Upgrade `maven wrapper` to `3.5.3`
  - Upgrade `spring boot` to `1.5.12`


## [0.28.0]
### Added
- Add new CoffeeNet Logo for favicon and in the navigation

### Changed
- Dependency
  - Upgrade `maven wrapper` to `3.5.2`
  - Upgrade `spring boot` to `1.5.10`
  - Upgrade `spring-cloud-dependencies` to `Dalston.SR5`


## [0.27.1]
### Changed
- Dependency
  - Downgrade `spring-cloud-dependencies` to `Dalston.SR4`
    - Because of problems with the `EurekaInstanceConfigBean` generation


## [0.27.0]
### Changed
- Dependency
  - Upgrade `spring boot` to `1.5.9`
  - Upgrade to `spring-cloud-dependencies` to `Edgware.RELEASE`
    - For changelog: https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Edgware-Release-Notes


## [0.26.0]
### Changed
- Dependency
  - PUT parameters will not be sent -> back to spring version 4.3.9
    (https://jira.spring.io/browse/SPR-15828)
  - Upgrade to `spring-cloud-dependencies` to `Dalston.SR3`


## [0.25.0]
### Added
- Add version information of starter and application
  in navigation

### Changed
- Upgrade `spring boot` to `1.5.6`
- Upgrade `spring-cloud-dependencies` to `Dalston.SR2`

### Fixed
- Fix not usable `spring-boot-devtools` dependency
  - Declare
    `CoffeeNetDiscoveryInstanceProperties`,
    `CoffeeNetDiscoveryClientProperties`
    in public scope because of spring devtools cannot wrap
    the class properly in non-public scope


## [0.24.0]
### Changed
- Upgrade `logback-gelf-appender` to `1.3.1`
- Renaming
  - `coffeenet-starter-web` -> `coffeenet-starter-navigation`
  - `coffeenet-starter-web-javascript` -> `coffeenet-starter-navigation-javascript`
  - `coffeenet-starter-web-thymeleaf` -> `coffeenet-starter-navigation-thymeleaf`

### Fixed
- Fix not available discovery dependency in
  autoconfigure for relations between auto configures
- Fix auto completion of `coffeenet.discovery.*` in your IDE


## [0.23.0]
### Changed
- Upgrade to `spring-cloud-dependencies` to Dalston.SR1
- Upgrade navigation bar to 0.12.1


## [0.22.1]
### Changed
- Use `EurekaDiscoveryClient` as guard for `starter-discovery`
  instead of the `DiscoveryClient` that is also used by other libraries.


## [0.22.0]
### Added
- Add missing `spring-boot-starter-web` in `starter-web-javascript`

### Changed
- Upgrade navigation bar to 0.12.0
  - Remove bootstrap dependency


## [0.21.0]
### Added
- Starters now complete independent usable from each other

### Changed
- Security Starter: admin and user in development mode both have now the role "USER"
- Upgrade to spring boot 1.5.4

### Removed
- Remove coffeenet-starter-application


## [0.20.0]
### Added
- New global CoffeeNet banner
- New favicon in the default resource directory

### Changed
- Rename 'starter-sso' to 'starter-security'
- Upgrade to spring-cloud-dependencies Camden.SR7


## [0.19.0]
### Changed
- Upgrade to spring boot 1.5.3
- Upgrade to spring-cloud-dependencies Camden.SR6
  - Add @Validated to all @ConfigurationProperties with Validation
- Change thymeleaf "main" fragment name to "coffeenet-main"
- Change thymeleaf "scripts" fragment name to "coffeenet-scripts"
- Prefix thymeleaf intern fragment names with "coffeenet-*"


## [0.18.1]
### Added
- Add check to start `CoffeeNetWebAutoConfiguration` configuring when `coffeenet-starter-web` is used


## [0.18.0]
### Added
- Add Security (SSO) and Discovery Starter are now optional for the Web Starter

### Changed
- Upgrade logback-gelf-appender to 1.3.0

### Fixed
- Fix to mark the opened application in the navigation bar when url only starts with the given url


## [0.17.0]
### Added
- Add parent pom functionality for starters **and** applications
- Add only show "Anwendungen" headline in navigation bar if an application is present

### Changed
- Upgrade to spring-cloud-dependencies Camden.SR5

### Fixed
- Fix to mark the opened application in the navigation bar in thymeleaf template
- Fix to improve guards in navigation bar to handle null values


## [0.16.2]
### Changed
- No user is needed to provide `CoffeeNetWeb`


## [0.16.1]
### Fixed
- Fix ignoring all service instances that are not from eureka


## [0.16.0]
### Added
- Add `CoffeeNetAppService` can be queried to find the `CoffeeNetApp` you need

### Changed
- Upgrade to spring boot 1.5.1

### Removed
- Remove bootstrap dependency from navigation bar
- Remove deprecated `coffeenet/user` and `coffeenet/apps` endpoints for the javascript navigation bar

### Fixed
- Fix get `CoffeeNetApps` in discovery starter ordered by their names
- Fix hamburger menu in thymeleaf starter


## [0.15.1]
### Changed
- Upgrade to Spring Boot 1.4.4
- Upgrade to Spring Cloud Camden.SR4

### Fixed
- Fix to only configure web starters when security and discovery is available


## [0.15.0]
### Added
- Use one AutoConfiguration per starter type
  - New package structure
- Add Web Starters
  - Thymeleaf Starter
  - Javascript Starter


[Unreleased]: https://github.com/coffeenet/coffeenet-starter/compare/v0.39.0...HEAD
[0.39.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.38.2...v0.39.0
[0.38.2]: https://github.com/coffeenet/coffeenet-starter/compare/v0.38.1...v0.38.2
[0.38.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.38.0...v0.38.1
[0.38.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.37.0...v0.38.0
[0.37.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.36.0...v0.37.0
[0.36.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.35.0...v0.36.0
[0.35.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.34.0...v0.35.0
[0.34.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.33.1...v0.34.0
[0.33.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.33.0...v0.33.1
[0.33.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.32.0...v0.33.0
[0.32.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.31.0...v0.32.0
[0.31.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.30.0...v0.31.0
[0.30.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.29.0...v0.30.0
[0.29.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.28.0...v0.29.0
[0.28.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.27.1...v0.28.0
[0.27.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.27.0...v0.27.1
[0.27.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.26.0...v0.27.0
[0.26.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.25.0...v0.26.0
[0.25.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.24.0...v0.25.0
[0.24.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.23.0...v0.24.0
[0.23.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.22.1...v0.23.0
[0.22.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.22.0...v0.22.1
[0.22.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.21.0...v0.22.0
[0.21.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.20.0...v0.21.0
[0.20.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.19.0...v0.20.0
[0.19.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.18.1...v0.19.0
[0.18.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.18.0...v0.18.1
[0.18.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.17.0...v0.18.0
[0.17.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.16.2...v0.17.0
[0.16.2]: https://github.com/coffeenet/coffeenet-starter/compare/v0.16.1...v0.16.2
[0.16.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.16.0...v0.16.1
[0.16.0]: https://github.com/coffeenet/coffeenet-starter/compare/v0.15.1...v0.16.0
[0.15.1]: https://github.com/coffeenet/coffeenet-starter/compare/v0.15.0...v0.15.1
