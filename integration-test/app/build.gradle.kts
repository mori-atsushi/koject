plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()
    js(IR) {
        moduleName = "integration-test-app"
        nodejs()
        browser()
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchos()
    watchosSimulatorArm64()
    tvos()
    tvosSimulatorArm64()

    mingwX64()
    linuxX64()
    linuxArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":koject-core"))
                implementation(kotlin("test"))
                implementation(project(":integration-test:lib1"))
                implementation(project(":integration-test:lib3"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":koject-test"))
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
            dependsOn(iosTest)
        }

        val watchosMain by getting {
            dependsOn(nativeMain)
        }

        val watchosTest by getting {
            dependsOn(nativeTest)
        }

        val watchosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val watchosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }

        val tvosMain by getting {
            dependsOn(nativeMain)
        }

        val tvosTest by getting {
            dependsOn(nativeTest)
        }

        val tvosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val tvosSimulatorArm64Test by getting {
            dependsOn(iosTest)
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

        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }

        val linuxArm64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxArm64Test by getting {
            dependsOn(nativeTest)
        }
    }
}

dependencies {
    kotlin.sourceSets.forEach { sourceSet ->
        if (sourceSet.name.endsWith("Main")) {
            val name = sourceSet.name.substringBefore("Main")
            val configuration = "ksp${name.replaceFirstChar { it.uppercase() }}"
            if (configurations.any { it.name == configuration }) {
                add(configuration, project(":processor:app"))
            }
        }
        if (sourceSet.name.endsWith("Test")) {
            val name = sourceSet.name.substringBefore("Test")
            val configuration = "ksp${name.replaceFirstChar { it.uppercase() }}Test"
            if (configurations.any { it.name == configuration }) {
                add(configuration, project(":processor:app"))
            }
        }
    }
}

ksp {
    arg("measureDuration", "true")
}
