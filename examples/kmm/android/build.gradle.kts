plugins {
    alias(libs.plugins.android.application)
    kotlin("android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.moriatsushi.koject.example.kmm"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.moriatsushi.koject.example.kmm"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.moriatsushi.koject.example.kmm.TestRunner"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.androidx.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":examples:kmm:shared"))
    implementation(project(":examples:kmm:infrastructure"))
    implementation(project(":android:koject-android-core"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")

    androidTestImplementation(project(":koject-test"))
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")
    androidTestImplementation("androidx.test:runner:1.5.2")
    kspAndroidTest(project(":processor:app"))
}
