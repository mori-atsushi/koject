package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Named
import com.moriatsushi.koject.Provides

@Named("name1")
@Provides
fun provideNamedClass1(): NamedClass {
    return NamedClass("name1")
}

@Named("name2")
@Provides
fun provideNamedClass2(): NamedClass {
    return NamedClass("name2")
}

@Provides
fun provideClass(): NamedClass {
    return NamedClass("no name")
}

data class NamedClass(
    val name: String,
)

@Named("name1")
@Provides
fun provideNameInterface1(): NamedInterface {
    return object : NamedInterface {
        override val name: String = "name1"
    }
}

@Named("name2")
@Provides
fun provideNamedInterfaceInterface2(): NamedInterface {
    return object : NamedInterface {
        override val name: String = "name2"
    }
}

interface NamedInterface {
    val name: String
}

@Provides
class NamedHolderClass(
    @Named("name1")
    val class1: NamedClass,
    @Named("name2")
    val class2: NamedClass,
    val noName: NamedClass,
    @Named("name1")
    val interface1: NamedInterface,
    @Named("name2")
    val interface2: NamedInterface,
)

@Provides
fun provideNamedHolderInterface(
    @Named("name1")
    class1: NamedClass,
    @Named("name2")
    class2: NamedClass,
    noName: NamedClass,
    @Named("name1")
    interface1: NamedInterface,
    @Named("name2")
    interface2: NamedInterface,
): NamedHolderInterface {
    return object : NamedHolderInterface {
        override val class1: NamedClass = class1
        override val class2: NamedClass = class2
        override val noName: NamedClass = noName
        override val interface1: NamedInterface = interface1
        override val interface2: NamedInterface = interface2
    }
}

interface NamedHolderInterface {
    val class1: NamedClass
    val class2: NamedClass
    val noName: NamedClass
    val interface1: NamedInterface
    val interface2: NamedInterface
}
