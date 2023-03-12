package com.moriatsushi.koject.android

import android.app.Application
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.KojectBuilder
import com.moriatsushi.koject.start

/**
 * Set the android application to provide.
 *
 * Make sure to call it in Koject.[start].
 */
@OptIn(ExperimentalKojectApi::class)
fun KojectBuilder.application(application: Application) {
    val extras = AndroidExtras(application)
    addExtras(extras)
}
