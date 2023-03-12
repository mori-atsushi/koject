package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.KojectBuilder

@InternalKojectApi
class KojectBuilderImpl : KojectBuilder {
    val extras = mutableSetOf<Any>()

    @OptIn(ExperimentalKojectApi::class)
    override fun addExtras(extras: Any) {
        this.extras.add(extras)
    }
}
