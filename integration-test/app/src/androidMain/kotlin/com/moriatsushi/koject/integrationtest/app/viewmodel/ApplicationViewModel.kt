package com.moriatsushi.koject.integrationtest.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent

@Provides
@ViewModelComponent
class ApplicationViewModel(
    application: Application,
) : AndroidViewModel(application)

@Provides
@ViewModelComponent
class ApplicationWithSavedStateViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application)
