plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    id("app.cash.sqldelight") version "2.0.0-alpha05"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":examples:kmm:ui"))
                api(project(":examples:kmm:data"))
                implementation(project(":examples:kmm:infrastructure"))

                implementation(project(":koject-core"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":android:koject-android-core"))
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(project(":koject-test"))
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.example.kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    add("kspAndroid", project(":processor:app"))
    add("kspIosX64", project(":processor:app"))
    add("kspIosArm64", project(":processor:app"))
    add("kspIosSimulatorArm64", project(":processor:app"))
}

ksp {
    arg("measureDuration", "true")
}
