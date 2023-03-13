package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moriatsushi.koject.Qualifier
import kotlinx.coroutines.CoroutineScope

/**
 * Get a [ViewModel]s [CoroutineScope] that the same as [viewModelScope].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ViewModelCoroutineScope
