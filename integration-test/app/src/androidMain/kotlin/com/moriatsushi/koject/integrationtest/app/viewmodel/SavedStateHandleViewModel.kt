@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Dynamic
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides

@Provides
class SavedStateHandleViewModel(
    @Dynamic
    val savedStateHandle: SavedStateHandle,
) : ViewModel()
