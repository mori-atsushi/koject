import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.publish)
    alias(libs.plugins.dokka)
}

kotlin {
    jvm()
    js(IR) {
        nodejs()
        browser()
    }
    wasm {
        binaries.executable()
        nodejs()
        browser {
            commonWebpackConfig {
                experiments = mutableSetOf("topLevelAwait")
            }
        }
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchos()
    watchosSimulatorArm64()
    watchosDeviceArm64()
    tvos()
    tvosSimulatorArm64()

    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    mingwX64()
    linuxX64()
    linuxArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":koject-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val jvmTest by getting {
            dependsOn(commonTest)
        }

        val jsMain by getting {
            dependsOn(commonMain)
        }

        val jsTest by getting {
            dependsOn(commonTest)
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

        val watchosMain by getting {
            dependsOn(nativeMain)
        }

        val watchosTest by getting {
            dependsOn(nativeTest)
        }

        val tvosMain by getting {
            dependsOn(nativeMain)
        }

        val tvosTest by getting {
            dependsOn(nativeTest)
        }

        val mingwX64Main by getting {
            dependsOn(nativeMain)
        }

        val mingwX64Test by getting {
            dependsOn(nativeTest)
        }

        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }

        val wasmMain by getting {
            dependsOn(nativeMain)
        }

        val wasmTest by getting {
            dependsOn(nativeTest)
        }

        all {
            languageSettings.optIn("com.moriatsushi.koject.internal.InternalKojectApi")
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "20.2.0"
    versions.webpack.version = "5.76.2"
}
