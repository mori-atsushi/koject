plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()
    js(IR) {
        moduleName = "koject-integration-test-lib"
        nodejs()
        browser()
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(commonMain)
        }

        val nativeMain by creating {
            dependsOn(commonMain)
        }

        val iosMain by getting {
            dependsOn(nativeMain)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val macosX64Main by getting {
            dependsOn(nativeMain)
        }

        val macosArm64Main by getting {
            dependsOn(nativeMain)
        }
    }
}

dependencies {
    add("kspJvm", project(":processor:app"))
    add("kspJs", project(":processor:app"))
    add("kspIosX64", project(":processor:app"))
    add("kspIosArm64", project(":processor:app"))
    add("kspIosSimulatorArm64", project(":processor:app"))
    add("kspMacosX64", project(":processor:app"))
    add("kspMacosArm64", project(":processor:app"))
}
