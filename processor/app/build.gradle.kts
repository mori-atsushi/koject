plugins {
    kotlin("multiplatform")
    alias(libs.plugins.publish)
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(libs.ksp.processor.api)
                implementation(project(":processor:common"))
            }
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }
    }
}
