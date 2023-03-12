package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.inject

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
    return viewModelFactory {
        initializer {
            val extras = ViewModelComponentExtras(this)
            inject<VM>(qualifier, extras)
        }
    }
}