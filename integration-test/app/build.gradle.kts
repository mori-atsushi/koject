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

    mingwX64()
    mingwX86()
    linuxX64()
    linuxArm32Hfp()
    linuxMips32()

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
    add("kspMingwX64", project(":processor:app"))
    add("kspMingwX86", project(":processor:app"))
    add("kspLinuxX64", project(":processor:app"))
    add("kspLinuxArm32Hfp", project(":processor:app"))
    add("kspLinuxMips32", project(":processor:app"))
}
