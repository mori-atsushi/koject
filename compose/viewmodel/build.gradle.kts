plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.publish)
}

android {
    namespace = "com.moriatsushi.koject.android.viewmodel"
    compileSdk = 33
    defaultConfig {
        minSdk = 14
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
