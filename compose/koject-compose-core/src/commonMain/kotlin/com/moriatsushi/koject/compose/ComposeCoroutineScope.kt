package com.moriatsushi.koject.compose

import androidx.compose.runtime.rememberCoroutineScope
import com.moriatsushi.koject.Qualifier
import kotlinx.coroutines.CoroutineScope

/**
 * Get a Composable [CoroutineScope] that the same as [rememberCoroutineScope].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class ComposeCoroutineScope
