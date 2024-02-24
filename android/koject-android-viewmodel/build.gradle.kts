plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.publish)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.AZUL)
    }
}

android {
    namespace = "com.moriatsushi.koject.android.viewmodel"
    compileSdk = 34
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
    }
}

dependencies {
    api(project(":koject-core"))
    ksp(project(":processor:lib"))
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    testImplementation(kotlin("test"))
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.robolectric)
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", project.name)
}
