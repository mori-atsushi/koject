plugins {
    alias(libs.plugins.android.application)
    kotlin("android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.moriatsushi.koject.integrationtest.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moriatsushi.koject.integrationtest.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.androidx.compose.compiler.get()
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":android:koject-android-core"))
    implementation(project(":android:koject-android-activity"))
    implementation(project(":android:koject-android-fragment"))
    implementation(project(":compose:koject-compose-core"))
    implementation(project(":compose:koject-compose-viewmodel"))
    ksp(project(":processor:app"))

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.fragment.testing)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui)
    testImplementation(kotlin("test"))
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.robolectric)
}
