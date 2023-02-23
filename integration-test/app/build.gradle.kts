plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(kotlin("test"))
                implementation(project(":integration-test:lib"))
            }
        }

        val commonTest by getting {
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
