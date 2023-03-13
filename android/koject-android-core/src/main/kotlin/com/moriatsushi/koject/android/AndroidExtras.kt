package com.moriatsushi.koject.android

import android.app.Application
import android.content.Context
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.extras.KojectExtras

@OptIn(ExperimentalKojectApi::class)
internal class AndroidExtras(
    @Singleton
    val application: Application,
) : KojectExtras {
    @Singleton
    val context: Context = application
}
