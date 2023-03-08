package com.moriatsushi.koject.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriatsushi.koject.android.viewmodel.kojectViewModelFactory

/**
 * Returns an existing [ViewModel] or creates a new one by Koject
 */
@Composable
inline fun <reified VM : ViewModel> injectViewModel(
    qualifier: Any? = null,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    extras: CreationExtras = if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
        viewModelStoreOwner.defaultViewModelCreationExtras
    } else {
        CreationExtras.Empty
    },
): VM {
    val factory = remember(qualifier) {
        kojectViewModelFactory<VM>(qualifier)
    }
    return viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        factory = factory,
        extras = extras,
    )
}
