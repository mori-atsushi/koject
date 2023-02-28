---
sidebar_position: 1
---

# Setup

## Multiplatform

You need to enable [KSP](https://github.com/google/ksp).

```diff title="build.gradle.kts"
plugins {
    kotlin("multiplatform")
+    id("com.google.devtools.ksp") version "<ksp-version>"
}

kotlin {
    android()
    jvm()
    js(IR) {
        nodejs()
        browser()
    }
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
+                implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha06")
            }
        }
    }
}

dependencies {
    // Add it according to your targets.
+    val processor = "com.moriatsushi.koject:koject-processor-app:1.0.0-alpha06"
+    add("kspAndroid", processor)
+    add("kspJvm", processor)
+    add("kspJs", processor)
+    add("kspIosX64", processor)
+    add("kspIosArm64", processor)
}
```

## Single platform

Inject can also be used on a single platform.

```diff title="build.gradle.kts"
plugins {
    kotlin("<target>")
+    id("com.google.devtools.ksp") version "<ksp-version>"
}

dependencies {
+    implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha06")
+    ksp("com.moriatsushi.koject:koject-processor-app1.0.0-alpha06")
}
```

## Library module
Use `koject-processor-lib` to avoid generating `Container` in library modules.

### Multiplatform

```diff title="build.gradle.kts"
dependencies {
    // Add it according to your targets.
-    val processor = "com.moriatsushi.koject:koject-processor-app:1.0.0-alpha06"
+    val processor = "com.moriatsushi.koject:koject-processor-lib:1.0.0-alpha06"
    add("kspAndroid", processor)
    add("kspJvm", processor)
    add("kspJs", processor)
    add("kspIosX64", processor)
    add("kspIosArm64", processor)
}
```

### single platform

```diff title="build.gradle.kts"
dependencies {
    implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha06")
-    ksp("com.moriatsushi.koject:koject-processor-app:1.0.0-alpha06")
+    ksp("com.moriatsushi.koject:koject-processor-lib:1.0.0-alpha06")
}
```

## Version Catalog
Copy the following snippets if you are using [gradle verion catalog](https://docs.gradle.org/current/userguide/platforms.html).

```xml title="libs.versions.toml"
[versions]
koject = "1.0.0-alpha06"

[libraries]
koject-core = { group = "com.moriatsushi.koject", name = "koject-core", version.ref = "koject" }
koject-processor-app = { group = "com.moriatsushi.koject", name = "koject-processor-app", version.ref = "koject" }
koject-processor-lib = { group = "com.moriatsushi.koject", name = "koject-processor-lib", version.ref = "koject" }
```
