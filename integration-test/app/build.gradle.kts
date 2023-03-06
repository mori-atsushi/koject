plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.application)
}

kotlin {
    android()
    jvm()
    js(IR) {
        moduleName = "koject-integration-test-app"
        nodejs()
        browser()
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    mingwX64()
    mingwX86()
    linuxX64()
    linuxArm32Hfp()
    linuxMips32()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(kotlin("test"))
                implementation(project(":integration-test:lib"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting

        val jvmTest by getting {
            dependsOn(commonTest)
        }

        val androidMain by getting {
            dependsOn(jvmMain)
            dependencies {
                implementation(project(":android:viewmodel"))
                implementation(project(":compose:core"))
                implementation(project(":compose:viewmodel"))
                implementation(libs.androidx.activity)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.fragment)
                implementation(libs.androidx.fragment.testing)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.compose.ui)
            }
        }

        val androidUnitTest by getting {
            dependsOn(jvmTest)
            dependencies {
                implementation(libs.androidx.test.core)
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.compose.ui.test)
                implementation(libs.robolectric)
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
        }

        val nativeTest by creating {
            dependsOn(commonTest)
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val iosMain by getting {
            dependsOn(nativeMain)
        }

        val iosTest by getting {
            dependsOn(nativeTest)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosSimulatorArm64Test by getting {
            dependsOn(iosMain)
        }

        val macosX64Main by getting {
            dependsOn(nativeMain)
        }

        val macosX64Test by getting {
            dependsOn(nativeTest)
        }

        val macosArm64Main by getting {
            dependsOn(nativeMain)
        }

        val macosArm64Test by getting {
            dependsOn(nativeTest)
        }

        val mingwX64Main by getting {
            dependsOn(nativeMain)
        }

        val mingwX64Test by getting {
            dependsOn(nativeTest)
        }

        val mingwX86Main by getting {
            dependsOn(nativeMain)
        }

        val mingwX86Test by getting {
            dependsOn(nativeTest)
        }

        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }

        val linuxArm32HfpMain by getting {
            dependsOn(nativeMain)
        }

        val linuxArm32HfpTest by getting {
            dependsOn(nativeTest)
        }

        val linuxMips32Main by getting {
            dependsOn(nativeMain)
        }

        val linuxMips32Test by getting {
            dependsOn(nativeTest)
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.integrationtest.app"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
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
    add("kspAndroid", project(":processor:app"))
    add("kspJvm", project(":processor:app"))
    add("kspJs", project(":processor:app"))
    add("kspIosX64", project(":processor:app"))
    add("kspIosArm64", project(":processor:app"))
    add("kspIosSimulatorArm64", project(":processor:app"))
    add("kspMacosX64", project(":processor:app"))
    add("kspMacosArm64", project(":processor:app"))
    add("kspMingwX64", project(":processor:app"))
    add("kspMingwX86", project(":processor:app"))
    add("kspLinuxX64", project(":processor:app"))
    add("kspLinuxArm32Hfp", project(":processor:app"))
    add("kspLinuxMips32", project(":processor:app"))
}
