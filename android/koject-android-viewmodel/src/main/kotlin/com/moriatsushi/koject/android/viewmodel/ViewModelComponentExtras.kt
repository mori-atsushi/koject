package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalKojectApi::class)
internal class ViewModelComponentExtras(
    private val extras: CreationExtras,
    private val coroutineScopeHolder: CoroutineScopeHolder,
) : ComponentExtras<ViewModelComponent> {
    val savedStateHandle: SavedStateHandle
        get() = extras.createSavedStateHandle()

    @ViewModelCoroutineScope
    val coroutineScope: CoroutineScope
        get() = coroutineScopeHolder.coroutineScope
}
