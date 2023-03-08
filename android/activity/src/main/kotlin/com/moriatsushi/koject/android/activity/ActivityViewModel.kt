package com.moriatsushi.koject.android.activity

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.android.viewmodel.kojectViewModelFactory

/**
 * Returns a [Lazy] delegate to access the Activity's [ViewModel]
 * provided by Koject
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.injectViewModels(
    qualifier: Any? = null,
): Lazy<VM> {
    val factoryProducer = {
        kojectViewModelFactory<VM>(qualifier)
    }
    return viewModels(factoryProducer = factoryProducer)
}
