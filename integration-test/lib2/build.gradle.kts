plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
}

kotlin {
    android()
    jvm()
    js(IR) {
        moduleName = "integration-test-lib2"
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
    add("kspAndroid", project(":processor:lib"))
    add("kspJvm", project(":processor:lib"))
    add("kspJs", project(":processor:lib"))
    add("kspIosX64", project(":processor:lib"))
    add("kspIosArm64", project(":processor:lib"))
    add("kspIosSimulatorArm64", project(":processor:lib"))
    add("kspMacosX64", project(":processor:lib"))
    add("kspMacosArm64", project(":processor:lib"))
    add("kspMingwX64", project(":processor:lib"))
    add("kspMingwX86", project(":processor:lib"))
    add("kspLinuxX64", project(":processor:lib"))
    add("kspLinuxArm32Hfp", project(":processor:lib"))
    add("kspLinuxMips32", project(":processor:lib"))
}
