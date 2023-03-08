package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
@Component
annotation class ViewModelComponent

@OptIn(ExperimentalKojectApi::class)
@ComponentExtras(ViewModelComponent::class)
internal class ViewModelComponentExtras(
    val savedStateHandle: SavedStateHandle,
)
