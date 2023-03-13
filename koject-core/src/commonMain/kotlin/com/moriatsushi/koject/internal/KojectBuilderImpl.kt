package com.moriatsushi.koject.internal

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.extras.KojectExtras

@OptIn(ExperimentalKojectApi::class)
@InternalKojectApi
class KojectBuilderImpl : KojectBuilder {
    val extras = mutableSetOf<KojectExtras>()

    override fun addExtras(extras: KojectExtras) {
        this.extras.add(extras)
    }
}
