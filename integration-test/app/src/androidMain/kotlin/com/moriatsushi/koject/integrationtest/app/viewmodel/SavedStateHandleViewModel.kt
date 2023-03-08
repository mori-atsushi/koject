package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent

@Provides
@ViewModelComponent
class SavedStateHandleViewModel(
    val savedStateHandle: SavedStateHandle,
) : ViewModel()
