pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "koject"
include(":core")
include(":processor:common")
include(":processor:app")
include(":processor:lib")
include(":integration-test:app")
include(":integration-test:lib")
include(":examples:kmm:android")
include(":examples:kmm:shared")
