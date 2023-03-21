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
            baseName = "infrastructure"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":examples:kmm:data"))

                implementation(project(":koject-core"))
                implementation(project(":koject-test"))
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
                implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")
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
                implementation("app.cash.sqldelight:native-driver:2.0.0-alpha05")
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
    namespace = "com.moriatsushi.koject.example.kmm.infrastructure"
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
    add("kspAndroid", project(":processor:lib"))
    add("kspIosX64", project(":processor:lib"))
    add("kspIosArm64", project(":processor:lib"))
    add("kspIosSimulatorArm64", project(":processor:lib"))
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.moriatsushi.koject.example.kmm.infrastructure.db")
        }
    }
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", project.name)
}
