import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Locale

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.AZUL)
    }
    jvm {
        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget = JvmTarget.JVM_11
            }
        }
    }
    js(IR) {
        moduleName = "integration-test-app"
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
                implementation(kotlin("test"))
                implementation(project(":integration-test:lib1"))
                implementation(project(":integration-test:lib3"))
            }
        }

        commonTest {
            dependencies {
                implementation(project(":koject-test"))
                implementation(kotlin("test"))
            }
        }
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

            // kspCommonMainMetadataTest is not found
            if (targetConfigSuffix != "CommonMainMetadata") {
                add("ksp${targetConfigSuffix}Test", project(":processor:lib"))
            }
        }
}

ksp {
    arg("measureDuration", "true")
}
