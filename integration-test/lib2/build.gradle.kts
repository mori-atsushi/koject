import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Locale

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
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
    jvm {
        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget = JvmTarget.JVM_11
            }
        }
    }
    js(IR) {
        moduleName = "integration-test-lib2"
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
        commonMain {
            dependencies {
                implementation(project(":koject-core"))
                implementation(project(":integration-test:lib3"))
                implementation(kotlin("test"))
            }
        }

        androidMain {
            dependsOn(jvmMain.get())
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.integrationtest.lib2"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
}

dependencies {
    fun String.capitalizeUS() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.US)
        else it.toString()
    }

    kotlin
        .targets
        .names
        .map { it.capitalizeUS() }
        .forEach { target ->
            val targetConfigSuffix = if (target == "Metadata") "CommonMainMetadata" else target
            add("ksp${targetConfigSuffix}", project(":processor:lib"))
        }
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", "integration-test-lib2")
}
