package com.moriatsushi.koject.integrationtest.app

import com.moriatsushi.koject.Provides

class ProvideCompanionObject {
    companion object {
        @Provides
        fun provide(): ProvideCompanionObject {
            return ProvideCompanionObject()
        }
    }
}

@Provides
data class ProvideCompanionObjectHolder(
    val value: ProvideCompanionObject,
)
