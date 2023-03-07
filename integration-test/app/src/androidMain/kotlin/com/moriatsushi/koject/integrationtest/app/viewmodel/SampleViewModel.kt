package com.moriatsushi.koject.integrationtest.app.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.integrationtest.app.AppClass1
import com.moriatsushi.koject.integrationtest.app.SingletonClass

@Provides
class SampleViewModel(
    private val appClass: AppClass1,
    private val singletonClass: SingletonClass,
) : ViewModel()

class NotProvidedViewModel : ViewModel()
