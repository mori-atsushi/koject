plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.publish)
    alias(libs.plugins.dokka)
}

android {
    namespace = "com.moriatsushi.koject.compose.viewmodel"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.androidx.compose.compiler.get()
    }
}

kotlin {
    sourceSets {
        all {
            languageSettings.optIn("com.moriatsushi.koject.internal.InternalKojectApi")
        }
    }
}

dependencies {
    api(project(":koject-core"))
    api(project(":android:koject-android-viewmodel"))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
