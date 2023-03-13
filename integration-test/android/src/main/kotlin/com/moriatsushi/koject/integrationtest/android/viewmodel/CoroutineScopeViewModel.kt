package com.moriatsushi.koject.integrationtest.android.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent
import com.moriatsushi.koject.android.viewmodel.ViewModelCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Provides
@ViewModelComponent
class CoroutineScopeViewModel(
    @ViewModelCoroutineScope
    val coroutineScope: CoroutineScope,
) : ViewModel()

@Provides
@ViewModelComponent
class ForViewModel(
    @ViewModelCoroutineScope
    val coroutineScope: CoroutineScope,
)

@Provides
@ViewModelComponent
class ForViewModelHolder(
    val forViewModel: ForViewModel,
) : ViewModel()
