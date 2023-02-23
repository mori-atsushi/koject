plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()
    // not working in Kotlin/JS
    // js(IR) {
    //     moduleName = "koject-integration-test-app"
    //     nodejs()
    //     browser()
    // }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        // val jsMain by getting {
        //     dependsOn(commonMain)
        // }

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
    add("kspJvm", project(":processor:lib"))
    // add("kspJs", project(":processor:lib"))
    add("kspIosX64", project(":processor:lib"))
    add("kspIosArm64", project(":processor:lib"))
    add("kspIosSimulatorArm64", project(":processor:lib"))
    add("kspMacosX64", project(":processor:lib"))
    add("kspMacosArm64", project(":processor:lib"))
}
