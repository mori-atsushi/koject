import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.AZUL)
    }

    androidTarget {
        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget = JvmTarget.JVM_11
            }
        }
    }
    jvm("desktop") {
        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget = JvmTarget.JVM_11
            }
        }
    }
    js(IR) {
        moduleName = "integration-test-compose"
        browser()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                api(project(":compose:koject-compose-core"))
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val commonJvmTest by creating {
            dependsOn(commonTest.get())
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
            }
        }

        androidMain {
            dependencies {
                api(project(":android:koject-android-core"))
                implementation(libs.androidx.activity)
            }
        }

        val androidUnitTest by getting {
            dependsOn(commonJvmTest)
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
                implementation(libs.androidx.test.core)
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.robolectric)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val desktopTest by getting {
            dependsOn(commonJvmTest)
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
            }
        }

        jsTest {
            dependencies {
                implementation(compose.html.testUtils)
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    kotlinOptions {
        // 'expect'/'actual' classes (including interfaces, objects, annotations, enums,
        // and 'actual' typealiases) are in Beta.
        // You can use -Xexpect-actual-classes flag to suppress this warning.
        // Also see: https://youtrack.jetbrains.com/issue/KT-61573
        freeCompilerArgs +=
            listOf(
                "-Xexpect-actual-classes",
            )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
