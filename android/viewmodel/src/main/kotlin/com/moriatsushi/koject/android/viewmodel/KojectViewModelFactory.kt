package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.internal.InternalKojectApi

@OptIn(ExperimentalKojectApi::class)
@InternalKojectApi
inline fun <reified VM : ViewModel> kojectViewModelFactory(
    qualifier: Any? = null,
): ViewModelProvider.Factory {
    return viewModelFactory {
        initializer {
            val extras = createComponentExtras(this)
            inject<VM>(qualifier, extras)
        }
    }
}

@PublishedApi
internal fun createComponentExtras(
    viewModelExtras: CreationExtras,
): ViewModelComponentExtras {
    return ViewModelComponentExtras(
        viewModelExtras.createSavedStateHandle(),
    )
}
