plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.publish)
    alias(libs.plugins.dokka)
}

kotlin {
    android()
    jvm("desktop")
    js(IR) {
        moduleName = "compose-core"
        browser()
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchos()
    tvos()

    mingwX64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core"))
                implementation(compose.runtime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.compose"
    compileSdk = 33
    defaultConfig {
        minSdk = 14
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = false
    }
}
