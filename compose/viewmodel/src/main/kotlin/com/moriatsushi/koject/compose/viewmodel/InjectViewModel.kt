package com.moriatsushi.koject.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriatsushi.koject.inject

@Composable
inline fun <reified VM : ViewModel> injectViewModel(
    qualifier: Any? = null,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
): VM {
    val initializer: CreationExtras.() -> VM = remember(qualifier) {
        { inject(qualifier) }
    }
    return viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        initializer = initializer,
    )
}
