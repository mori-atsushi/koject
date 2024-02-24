import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    android()
    jvm("desktop")
    js(IR) {
        moduleName = "integration-test-compose"
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":compose:koject-compose-core"))
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmTest by creating {
            dependsOn(commonTest)
            dependencies {
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(project(":android:koject-android-core"))
                implementation(libs.androidx.activity)
            }
        }

        val androidUnitTest by getting {
            dependsOn(jvmTest)
            dependencies {
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(libs.androidx.test.core)
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.robolectric)
            }
        }

        val desktopMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val desktopTest by getting {
            dependsOn(jvmTest)
            dependencies {
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }

        val jsTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(compose.web.testUtils)
            }
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.integrationtest.compose"
    compileSdk = 34
    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = false
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    add("kspAndroid", project(":processor:app"))
    add("kspDesktop", project(":processor:app"))
    add("kspJs", project(":processor:app"))
}

// Workaround for KSP
// https://github.com/google/ksp/issues/1318
afterEvaluate {
    configurations
        .filter { it.name.startsWith("generatedByKspKotlinJs") && it.name.endsWith("DependenciesMetadata") }
        .forEach { configurations.remove(it) }
}
