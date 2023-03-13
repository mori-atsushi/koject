package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
@PublishedApi
internal class ViewModelComponentExtras(
    private val extras: CreationExtras,
) : ComponentExtras<ViewModelComponent> {
    val savedStateHandle: SavedStateHandle
        get() = extras.createSavedStateHandle()
}
