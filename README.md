# Koject  [🚧 Work in progress 🚧]
Koject is a DI Container library for Kotlin Multiplatform.

```kotlin
fun main() {
    Koject.start()

    val controller = inject<Controller>()
}

@Singleton
@Provides
class Api

@Singleton
@Provides
fun createDB(): DB {
    return DB.create()
}

@Singleton
@Provides
class Repository(
    private val api: Api,
    private val db: DB,
)

@Provides
fun Controller(
    private val repository: Repository
)
```

## Features

* Support [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
* Easy provide with annotations
* Code generation with [KSP](https://github.com/google/ksp)
* Support multi-module project

## Setup
### Multiplatform

You need to enable KSP.

<details open><summary>build.gradle.kts</summary>

```diff
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
+                implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha04")
            }
        }
    }
}

dependencies {
    // Add it according to your targets.
+    val processor = "com.moriatsushi.koject:koject-processor-app:1.0.0-alpha04"
+    add("kspAndroid", processor)
+    add("kspJvm", processor)
+    add("kspJs", processor)
+    add("kspIosX64", processor)
+    add("kspIosArm64", processor)
}
```
</details>

### Single platform

Inject can also be used on a single platform.

<details><summary>build.gradle.kts</summary>

```diff
plugins {
    kotlin("<target>")
+    id("com.google.devtools.ksp") version "<ksp-version>"
}

dependencies {
+    implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha04")
+    ksp("com.moriatsushi.koject:koject-processor-app:1.0.0-alpha04")
}
```

</details>

### Library module
Use `koject-processor-lib` to avoid generating `Container` in library modules.

<details open><summary>build.gradle.kts (Multiplatform)</summary>

```diff
dependencies {
    // Add it according to your targets.
-    val processor = "com.moriatsushi.koject:koject-processor-app:1.0.0-alpha04"
+    val processor = "com.moriatsushi.koject:koject-processor-lib:1.0.0-alpha04"
    add("kspAndroid", processor)
    add("kspJvm", processor)
    add("kspJs", processor)
    add("kspIosX64", processor)
    add("kspIosArm64", processor)
}
```

</details>

<details><summary>build.gradle.kts (single platform)</summary>

```diff
dependencies {
    implementation("com.moriatsushi.koject:koject-core:1.0.0-alpha04")
-    ksp("com.moriatsushi.koject:koject-processor-app:1.0.0-alpha04")
+    ksp("com.moriatsushi.koject:koject-processor-lib:1.0.0-alpha04")
}
```

</details>

## Usage
Add `@Provides` annotation to the class you want to provide.

```kotlin
@Provides
class Repository

@Provides
class Controller(
    private val repository: Repository
)
```

You can get an instance using `inject` after calling `Koject.start()`.

```kotlin
fun main() {
    Koject.start()

    val controller = inject<Controller>()
}
```

### Provide from functions
Any types can be provided from top-level functions with a `@Provides` annotation. This is useful when provide outside modules classes.

```kotlin
@Provides
fun createDB(): DB {
    return DB.create()
}
```

You can also provide from object functions.

```kotlin
object DBFactory {
    @Provides
    fun create(): DB {
        return DB.create()
    }
}
```

### Singleton Scope
By adding the `@Singleton` annotation, the instance will be created only once and reused within the application.

```kotlin
@Singleton
@Provides
class Api

@Singleton
@Provides
fun createDB(): DB {
    return DB.create()
}
```

Note that you can't inject a normally scope type into a singleton scope type.

```kotlin
@Provides
class NormalScope

@Singleton
@Provides
class SingletonScope(
    // cannot inject!
    private val normal: NormalScope
)
```

## TODO
This library is incomplete and the following features will be added later.

- [x] [Allow provide from function #18](https://github.com/Mori-Atsushi/koject/issues/18)
- [x] [Support singleton #19](https://github.com/Mori-Atsushi/koject/issues/19)
- [ ] [Allow provide same types #20](https://github.com/Mori-Atsushi/koject/issues/20)
- [ ] [Make type binding easier #21](https://github.com/Mori-Atsushi/koject/issues/21)
- [ ] [Make compile-time error messages easier to understand #22](https://github.com/Mori-Atsushi/koject/issues/22)
- [ ] [Add example projects #29](https://github.com/Mori-Atsushi/koject/issues/29)
- [ ] [Complete documentation #27](https://github.com/Mori-Atsushi/koject/issues/27)
