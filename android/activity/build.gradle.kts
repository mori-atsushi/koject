plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
}

android {
    namespace = "com.moriatsushi.koject.android.activity"
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
    api(project(":core"))
    api(project(":android:viewmodel"))
    implementation(libs.androidx.activity)
}
