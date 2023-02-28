# Start Koject
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

## Provide from functions
Any types can be provided from top-level functions with a `@Provides` annotation. This is useful when provide outside modules classes.

```kotlin
@Provides
fun provideDB(): DB {
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

## Singleton Scope
By adding the `@Singleton` annotation, the instance will be created only once and reused within the application.

```kotlin
@Singleton
@Provides
class Api

@Singleton
@Provides
fun provideDB(): DB {
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
