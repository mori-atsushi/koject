package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.inject
import kotlin.reflect.KClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Instantiate [ViewModel]s provided by Koject.
 *
 * @param qualifier Qualifier for identification.
 * Specify the instantiation of the annotation with [Qualifier].
 */
@OptIn(ExperimentalKojectApi::class)
inline fun <reified VM : ViewModel> kojectViewModelFactory(
    qualifier: Any? = null,
): ViewModelProvider.Factory {
    return kojectViewModelFactory(VM::class) {
        inject(qualifier, it)
    }
}

@PublishedApi
internal fun <VM : ViewModel> kojectViewModelFactory(
    clazz: KClass<VM>,
    initializer: (ViewModelComponentExtras) -> VM,
): ViewModelProvider.Factory {
    return viewModelFactory {
        addInitializer(clazz) {
            val holder = CoroutineScopeHolder {
                SupervisorJob() + Dispatchers.Main.immediate
            }
            val extras = ViewModelComponentExtras(this, holder)
            initializer(extras).apply {
                addCloseable(holder)
            }
        }
    }
}
