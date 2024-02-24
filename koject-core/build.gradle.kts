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

    iosArm64()
    iosX64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    tvosX64()
    tvosSimulatorArm64()
    tvosArm64()
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    watchosDeviceArm64()

    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()

    mingwX64()
    linuxX64()
    linuxArm64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        all {
            languageSettings.optIn("com.moriatsushi.koject.internal.InternalKojectApi")
        }
    }
}
