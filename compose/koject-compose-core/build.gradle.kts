plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.publish)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

kotlin {
    android()
    jvm("desktop")
    js(IR) {
        browser()
    }
    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchos()
    tvos()

    mingwX64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":koject-core"))
                implementation(compose.runtime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.compose"
    compileSdk = 33
    defaultConfig {
        minSdk = 14
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    add("kspAndroid", project(":processor:lib"))
    add("kspDesktop", project(":processor:lib"))
    add("kspJs", project(":processor:lib"))
    add("kspIosX64", project(":processor:lib"))
    add("kspIosArm64", project(":processor:lib"))
    add("kspIosSimulatorArm64", project(":processor:lib"))
    add("kspMacosX64", project(":processor:lib"))
    add("kspMacosArm64", project(":processor:lib"))
    add("kspWatchosArm32", project(":processor:lib"))
    add("kspWatchosArm64", project(":processor:lib"))
    add("kspWatchosX64", project(":processor:lib"))
    add("kspTvosArm64", project(":processor:lib"))
    add("kspTvosX64", project(":processor:lib"))
    add("kspMingwX64", project(":processor:lib"))
    add("kspLinuxX64", project(":processor:lib"))
}

// Workaround for KSP
// https://github.com/google/ksp/issues/1318
afterEvaluate {
    configurations
        .filter { it.name.startsWith("generatedByKspKotlinJs") && it.name.endsWith("DependenciesMetadata") }
        .forEach { configurations.remove(it) }
}
