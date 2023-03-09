package com.moriatsushi.koject.android.activity

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import com.moriatsushi.koject.android.viewmodel.kojectViewModelFactory

/**
 * Returns a [Lazy] delegate to access the Activity's [ViewModel]
 * provided by Koject
 *
 * @param qualifier Qualifier for identification.
 * @param extrasProducer Create the default extras that will be
 * used to create the [ViewModel].
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.injectViewModels(
    qualifier: Any? = null,
    noinline extrasProducer: (() -> CreationExtras)? = null,
): Lazy<VM> {
    val factoryProducer = {
        kojectViewModelFactory<VM>(qualifier)
    }
    return viewModels(
        factoryProducer = factoryProducer,
        extrasProducer = extrasProducer,
    )
}
