import java.util.Locale

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.publish)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
}

kotlin {
    androidTarget()
    jvm("desktop")
    js(IR) {
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

    mingwX64()
    linuxX64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                api(project(":koject-core"))
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        androidMain {
            dependencies {
                implementation(compose.ui)
            }
        }
    }
}

android {
    namespace = "com.moriatsushi.koject.compose"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = false
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
        }
}

ksp {
    arg("measureDuration", "true")
    arg("moduleName", project.name)
}

// Workaround for KSP
// https://github.com/google/ksp/issues/1318
afterEvaluate {
    configurations
        .filter { it.name.startsWith("generatedByKspKotlinJs") && it.name.endsWith("DependenciesMetadata") }
        .forEach { configurations.remove(it) }
}
