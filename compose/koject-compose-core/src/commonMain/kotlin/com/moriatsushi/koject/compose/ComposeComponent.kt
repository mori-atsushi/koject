package com.moriatsushi.koject.compose

import androidx.compose.runtime.Composable
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.Component

/**
 * Mark it as a type to inject into [Composable].
 *
 * Additional available types:
 * * [kotlinx.coroutines.CoroutineScope] with @[ComposeCoroutineScope]
 * * android.content.Context with @ComposeContext (android only)
 */
@OptIn(ExperimentalKojectApi::class)
@Component
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class ComposeComponent
