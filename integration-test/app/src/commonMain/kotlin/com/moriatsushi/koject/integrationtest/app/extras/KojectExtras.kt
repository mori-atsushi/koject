@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.extras

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.extras.KojectExtras

@KojectExtras
class GlobalExtras(
    val extra: GlobalExtraClass,
)

class GlobalExtraClass

@Provides
class GlobalExtraHolder(
    val extra: GlobalExtraClass,
)
