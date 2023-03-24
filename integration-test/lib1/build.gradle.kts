plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
}

kotlin {
    android()
    jvm()
    js(IR) {
        moduleName = "integration-test-lib1"
        nodejs()
        browser()
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
                implementation(project(":koject-core"))
                implementation(project(":integration-test:lib2"))
                implementation(project(":integration-test:lib3"))
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting

        val androidMain by getting {
            dependsOn(jvmMain)
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.integrationtest.lib"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
}

dependencies {
    kotlin.sourceSets.forEach { sourceSet ->
        if (sourceSet.name.endsWith("Main")) {
            val name = sourceSet.name.substringBefore("Main")
            val configuration = "ksp${name.replaceFirstChar { it.uppercase() }}"
            if (configurations.any { it.name == configuration }) {
                add(configuration, project(":processor:lib"))
            }
        }
    }
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", "integration-test-lib1")
}
