package com.moriatsushi.koject.android.fragment

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
