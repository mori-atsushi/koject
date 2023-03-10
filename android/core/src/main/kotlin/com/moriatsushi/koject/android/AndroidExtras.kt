package com.moriatsushi.koject.android

import android.app.Application
import android.content.Context
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.extras.KojectExtras

@OptIn(ExperimentalKojectApi::class)
@KojectExtras
internal class AndroidExtras(
    @Singleton
    val application: Application,
) {
    @Singleton
    val context: Context = application
}
