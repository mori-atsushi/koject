# Koject
[![](https://img.shields.io/badge/Kotlin-Multiplatform-%237f52ff?logo=kotlin)](https://kotlinlang.org/docs/multiplatform.html)
[![](https://img.shields.io/maven-central/v/com.moriatsushi.koject/koject-core)](https://mvnrepository.com/artifact/com.moriatsushi.koject/koject-core)
[![](https://img.shields.io/github/license/mori-atsushi/koject)](https://github.com/Mori-Atsushi/koject/blob/main/LICENSE)

Koject is a DI Container Library for Kolin Multiplatform using KSP.

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
fun provideDB(): DB {
    return DB.create()
}

@Binds
@Singleton
@Provides
class RepositoryImpl(
    private val api: Api,
    private val db: DB,
): Repository

interface Repository

@Provides
class Controller(
    private val repository: Repository
)
```

## Features
* Support [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
* Easy provide with annotations
* Check dependency graph at compile time
* Code generation with [KSP](https://github.com/google/ksp)
* Support multi-module project

## Documents
* [Setup](https://mori-atsushi.github.io/koject/docs/setup)
* [Core](https://mori-atsushi.github.io/koject/docs/core)
  * [Start Koject](https://mori-atsushi.github.io/koject/docs/core/basic)
  * [Qualifier](https://mori-atsushi.github.io/koject/docs/core/qualifier)
  * [Binds](https://mori-atsushi.github.io/koject/docs/core/binds)
* [Test](https://mori-atsushi.github.io/koject/docs/test)
* [Android](https://mori-atsushi.github.io/koject/docs/android)
* [iOS (KMM)](https://mori-atsushi.github.io/koject/docs/ios)
* [Compose](https://mori-atsushi.github.io/koject/docs/compose)

## Examples
* [Koject TODO (KMM)](https://github.com/Mori-Atsushi/koject/tree/main/examples/kmm)
* [Now in Android App with Koject (Android)](https://github.com/mori-atsushi/nowinandroid)

## Related libraries
Koject is inspired by the following libraries.

* [google/dagger](https://github.com/google/dagger)
* [InsertKoinIO/koin](https://github.com/InsertKoinIO/koin)
* [evant/kotlin-inject](https://github.com/evant/kotlin-inject)
* [mars885/hilt-binder](https://github.com/mars885/hilt-binder)
