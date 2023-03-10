package com.moriatsushi.koject.integrationtest.app.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent

@Provides
@ViewModelComponent
@SuppressLint("StaticFieldLeak")
class ContextViewModel(
    val context: Context,
) : ViewModel()

@Provides
@ViewModelComponent
@SuppressLint("StaticFieldLeak")
class ContextWithSavedStateViewModel(
    val context: Context,
    val savedStateHandle: SavedStateHandle,
) : ViewModel()
