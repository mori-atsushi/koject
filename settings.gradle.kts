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
include(":koject-core")
include(":koject-test")
include(":processor:common")
include(":processor:app")
include(":processor:lib")
include(":android:koject-android-core")
include(":android:koject-android-activity")
include(":android:koject-android-fragment")
include(":android:koject-android-viewmodel")
include(":compose:koject-compose-core")
include(":compose:koject-compose-viewmodel")
include(":integration-test:app")
include(":integration-test:lib")
include(":integration-test:android")
include(":integration-test:compose")
include(":examples:kmm:android")
include(":examples:kmm:shared")
include(":examples:kmm:data")
include(":examples:kmm:infrastructure")
include(":examples:kmm:ui")
