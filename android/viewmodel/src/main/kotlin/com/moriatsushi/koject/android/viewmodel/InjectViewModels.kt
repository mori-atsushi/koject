package com.moriatsushi.koject.android.viewmodel

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import com.moriatsushi.koject.inject
import kotlin.reflect.KClass

/**
 * Returns a [Lazy] delegate to access the Activity's [ViewModel]
 * provided by Koject
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.injectViewModels(
    qualifier: Any? = null,
): Lazy<VM> {
    return internalInjectViewModels(
        qualifier = qualifier,
        ownerProducer = { this },
    )
}

/**
 * Returns a [Lazy] delegate to access the Fragment's [ViewModel]
 * provided by Koject
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.injectViewModels(
    qualifier: Any? = null,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
): Lazy<VM> {
    return internalInjectViewModels(
        qualifier = qualifier,
        ownerProducer = ownerProducer,
    )
}

/**
 * Returns a [Lazy] delegate to access parent activity's [ViewModel]
 * provided by Koject
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.injectActivityViewModels(
    qualifier: Any? = null,
): Lazy<VM> {
    return injectViewModels(
        qualifier = qualifier,
        ownerProducer = { requireActivity() },
    )
}

@PublishedApi
internal inline fun <reified VM : ViewModel> internalInjectViewModels(
    qualifier: Any? = null,
    noinline ownerProducer: () -> ViewModelStoreOwner,
): Lazy<VM> {
    val initializer = { inject<VM>(qualifier) }
    val clazz = VM::class
    return lazy(LazyThreadSafetyMode.NONE) {
        ownerProducer().get(clazz, initializer)
    }
}

@PublishedApi
internal fun <VM : ViewModel> ViewModelStoreOwner.get(
    clazz: KClass<VM>,
    initializer: () -> VM,
): VM {
    val factory = viewModelFactory {
        addInitializer(clazz) { initializer() }
    }
    val provider = ViewModelProvider(viewModelStore, factory)
    return provider[clazz.java]
}
