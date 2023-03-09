package com.moriatsushi.koject.android.fragment

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import com.moriatsushi.koject.android.viewmodel.kojectViewModelFactory

/**
 * Returns a [Lazy] delegate to access the Fragment's [ViewModel]
 * provided by Koject
 *
 * @param qualifier Qualifier for identification.
 * @param ownerProducer Specify [ViewModelStoreOwner] that controls the scope
 * and lifetime of the returned [ViewModel]. The default is the current [Fragment],
 * which can be changed to [ComponentActivity] or parent [Fragment].
 * @param extrasProducer Create the default extras that will be
 * used to create the [ViewModel].
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.injectViewModels(
    qualifier: Any? = null,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline extrasProducer: (() -> CreationExtras)? = null,
): Lazy<VM> {
    val factoryProducer = { kojectViewModelFactory<VM>(qualifier) }
    return viewModels(
        factoryProducer = factoryProducer,
        ownerProducer = ownerProducer,
        extrasProducer = extrasProducer,
    )
}

/**
 * Returns a [Lazy] delegate to access parent activity's [ViewModel]
 * provided by Koject
 *
 * @param qualifier Qualifier for identification.
 * @param extrasProducer Create the default extras that will be
 * used to create the [ViewModel].
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.injectActivityViewModels(
    qualifier: Any? = null,
    noinline extrasProducer: (() -> CreationExtras)? = null,
): Lazy<VM> {
    return injectViewModels(
        qualifier = qualifier,
        ownerProducer = { requireActivity() },
        extrasProducer = extrasProducer,
    )
}
