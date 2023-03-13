package com.moriatsushi.koject.android.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moriatsushi.koject.Qualifier
import kotlinx.coroutines.CoroutineScope

/**
 * Get a [Fragment]'s [CoroutineScope] that the same as [lifecycleScope].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class FragmentCoroutineScope

/**
 * Get a [Fragment] view's [CoroutineScope] that the same as
 * Fragment.viewLifecycleOwner.[lifecycleScope].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
annotation class FragmentViewCoroutineScope
