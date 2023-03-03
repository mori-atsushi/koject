# Koject
DI Container library for Kotlin Multiplatform.

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
* [Usage](https://mori-atsushi.github.io/koject/docs/usage)
  * [Start Koject](https://mori-atsushi.github.io/koject/docs/usage/basic)
  * [Qualifier](https://mori-atsushi.github.io/koject/docs/usage/qualifier)
  * [Binds](https://mori-atsushi.github.io/koject/docs/usage/binds)

## Examples
* [Koject TODO (KMM)](https://github.com/Mori-Atsushi/koject/tree/main/examples/kmm)

## Related libraries
Koject is inspired by the following libraries.

* [google/dagger](https://github.com/google/dagger)
* [InsertKoinIO/koin](https://github.com/InsertKoinIO/koin)
* [evant/kotlin-inject](https://github.com/evant/kotlin-inject)
* [mars885/hilt-binder](https://github.com/mars885/hilt-binder)
