package com.moriatsushi.koject.integrationtest.android.fragment

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.fragment.FragmentComponent

@FragmentComponent
@Provides
class ForFragment(
    val fragment: Fragment,
)

@FragmentComponent
@Provides
class ForFragmentWithActivity(
    val fragment: Fragment,
    val fragmentActivity: FragmentActivity,
    val componentActivity: ComponentActivity,
    val activity: Activity,
)

@FragmentComponent
@Provides
class ForFragmentHolder(
    val forFragment: ForFragment,
    val forFragmentWithActivity: ForFragmentWithActivity,
)
