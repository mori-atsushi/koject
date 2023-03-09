package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@PublishedApi
@ExperimentalKojectApi
@ComponentExtras(ViewModelComponent::class)
internal class ViewModelComponentExtras(
    private val extras: CreationExtras,
) {
    val savedStateHandle: SavedStateHandle
        get() = extras.createSavedStateHandle()
}
