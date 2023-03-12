package com.moriatsushi.koject.android.fragment

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.android.activity.ActivityContext
import com.moriatsushi.koject.component.ComponentExtras

@OptIn(ExperimentalKojectApi::class)
@PublishedApi
@ComponentExtras(FragmentComponent::class)
internal class FragmentComponentExtras(
    val fragment: Fragment,
) {
    val fragmentActivity: FragmentActivity
        get() = fragment.requireActivity()

    val componentActivity: ComponentActivity
        get() = fragmentActivity

    val activity: Activity
        get() = fragmentActivity

    @ActivityContext
    val context: Context
        get() = activity
}
