package com.moriatsushi.koject.integrationtest.android.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent
import com.moriatsushi.koject.integrationtest.android.NormalClass
import com.moriatsushi.koject.integrationtest.android.SingletonClass

@Provides
@ViewModelComponent
class SampleViewModel(
    private val normalClass: NormalClass,
    private val singletonClass: SingletonClass,
) : ViewModel()

class NotProvidedViewModel : ViewModel()
