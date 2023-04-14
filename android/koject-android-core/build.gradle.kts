plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.publish)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

android {
    namespace = "com.moriatsushi.koject.android"
    compileSdk = 33
    defaultConfig {
        minSdk = 14
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    api(project(":koject-core"))
    ksp(project(":processor:lib"))
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", project.name)
}
