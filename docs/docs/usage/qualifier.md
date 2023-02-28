# Qualifier
When you want to provide the same type, you can use `@Qualifier` annotations to distinguish between them.

First, define qualifier annotations with `@Qualifier`.

```kotlin
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ID1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ID2
```

Then, specify the qualifier annotations when providing.

```kotlin
@ID1
@Provide
fun provideDB1(): DB {
    DB.create("id1")
}

@ID1
@Provide
fun provideDB2(): DB {
    DB.create("id2")
}
```

Finally, you can use the qualifiers  to inject the instances you need.

```kotlin
class Repository(
    @ID1
    val db1: DB,
    @ID2
    val db2: DB
)
```
```kotlin
val db1 = inject<DB>(DB1())
val db2 = inject<DB>(DB2())
```

You can also use a `@Named` qualifier that distinguish by string.

```kotlin
@Named("db1")
@Provide
fun provideDB1(): DB {
    DB.create("id1")
}

@Named("db2")
@Provide
fun provideDB2(): DB {
    DB.create("id2")
}
```
```kotlin
class Repository(
    @Named("db1")
    val db1: DB,
    @Named("db2")
    val db2: DB
)
```
```kotlin
val db1 = inject<DB>("db1")
val db2 = inject<DB>("db2")
```
