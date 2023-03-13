package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides

@Provides
object ProvidableObject

@Binds
@Provides
object ProvidableObjectImpl : IProvidableObject

interface IProvidableObject

@Provides
class ProvidableObjectHolder(
    val value1: ProvidableObject,
    val value2: IProvidableObject,
    val value3: NestedObject,
    val value4: Companion,
) {
    @Provides
    object NestedObject

    @Provides
    companion object
}
