plugins {
    kotlin("multiplatform")
    alias(libs.plugins.publish)
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":koject-core"))
                implementation(project(":koject-test"))
                implementation(libs.ksp.processor.api)
                implementation(libs.kotlinpoet.ksp)
            }
            kotlin.srcDir("src/main/kotlin")
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlin.compile.testing.ksp)
            }
            kotlin.srcDir("src/test/kotlin")
        }

        all {
            languageSettings.optIn("com.moriatsushi.koject.internal.InternalKojectApi")
        }
    }
}
