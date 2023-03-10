package com.moriatsushi.koject.android

import android.app.Application
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

/**
 * Set the android application to provide.
 *
 * Make sure to call it before Koject.[start].
 */
@OptIn(ExperimentalKojectApi::class)
fun Koject.application(application: Application) {
    val extras = AndroidExtras(application)
    addExtras(extras)
}
