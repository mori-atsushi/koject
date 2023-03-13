package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component

/**
 * Can be provided as [ViewModel]s or types used by ViewModels
 * when used with @[Provides].
 *
 * ```
 * @Provides
 * @ViewModelComponent
 * class SampleViewModel(
 *     userRepository: UserRepository
 * ): ViewModel()
 * ```
 *
 * Can inject a [SavedStateHandle] using ViewModelComponent.
 *
 * ```
 * @Provides
 * @ViewModelComponent
 * class SavedStateViewModel(
 *     private val savedStateHandle: SavedStateHandle
 * ) : ViewModel()
 * ```
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ViewModelComponent
