package com.moriatsushi.koject.android.fragment

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.android.activity.ActivityContext
import com.moriatsushi.koject.component.ComponentExtras
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalKojectApi::class)
@PublishedApi
internal class FragmentComponentExtras(
    val fragment: Fragment,
) : ComponentExtras<FragmentComponent> {
    val fragmentActivity: FragmentActivity
        get() = fragment.requireActivity()

    val componentActivity: ComponentActivity
        get() = fragmentActivity

    val activity: Activity
        get() = fragmentActivity

    @ActivityContext
    val context: Context
        get() = activity

    @FragmentCoroutineScope
    val coroutineScope: CoroutineScope
        get() = fragment.lifecycleScope

    @FragmentViewCoroutineScope
    val viewCoroutineScope: CoroutineScope
        get() = fragment.viewLifecycleOwner.lifecycleScope
}
