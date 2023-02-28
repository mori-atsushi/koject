# Binds
By using a `@Binds` annotation, it is easy to provide as a supertypes.

```kotlin
@Binds
@Provides
class RepositoryImpl: Repository

interface Repository
```

This is a shortcut for the following implementation.

```kotlin
class RepositoryImpl: Repository

interface Repository

@Provides
fun provideRepository(): Repository {
    return RepositoryImpl()
}
```

If a type has multiple supertypes, use `to` parameter to specify the type.

```kotlin
@Binds(to = Type2::class)
@Provides
class Type: Type1, Type2

interface Type1
interface Type2
```
