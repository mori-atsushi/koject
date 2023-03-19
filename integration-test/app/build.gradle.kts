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

    mingwX64()
    mingwX86()
    linuxX64()
    linuxArm32Hfp()
    linuxMips32()

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

        val jvmMain by getting

        val jvmTest by getting {
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

        val mingwX86Main by getting {
            dependsOn(nativeMain)
        }

        val mingwX86Test by getting {
            dependsOn(nativeTest)
        }

        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }

        val linuxArm32HfpMain by getting {
            dependsOn(nativeMain)
        }

        val linuxArm32HfpTest by getting {
            dependsOn(nativeTest)
        }

        val linuxMips32Main by getting {
            dependsOn(nativeMain)
        }

        val linuxMips32Test by getting {
            dependsOn(nativeTest)
        }
    }
}

dependencies {
    add("kspJvm", project(":processor:app"))
    add("kspJvmTest", project(":processor:app"))
    add("kspJs", project(":processor:app"))
    add("kspJsTest", project(":processor:app"))
    add("kspIosX64", project(":processor:app"))
    add("kspIosX64Test", project(":processor:app"))
    add("kspIosArm64", project(":processor:app"))
    add("kspIosArm64Test", project(":processor:app"))
    add("kspIosSimulatorArm64", project(":processor:app"))
    add("kspIosSimulatorArm64Test", project(":processor:app"))
    add("kspMacosX64", project(":processor:app"))
    add("kspMacosX64Test", project(":processor:app"))
    add("kspMacosArm64", project(":processor:app"))
    add("kspMacosArm64Test", project(":processor:app"))
    add("kspMingwX64", project(":processor:app"))
    add("kspMingwX64Test", project(":processor:app"))
    add("kspMingwX86", project(":processor:app"))
    add("kspMingwX86Test", project(":processor:app"))
    add("kspLinuxX64", project(":processor:app"))
    add("kspLinuxX64Test", project(":processor:app"))
    add("kspLinuxArm32Hfp", project(":processor:app"))
    add("kspLinuxArm32HfpTest", project(":processor:app"))
    add("kspLinuxMips32", project(":processor:app"))
    add("kspLinuxMips32Test", project(":processor:app"))
}
