package com.moriatsushi.koject.integrationtest.android.fragment

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.activity.ActivityContext
import com.moriatsushi.koject.android.fragment.FragmentComponent
import com.moriatsushi.koject.android.fragment.FragmentCoroutineScope
import com.moriatsushi.koject.android.fragment.FragmentViewCoroutineScope
import kotlinx.coroutines.CoroutineScope

@FragmentComponent
@Provides
class ForFragment(
    val fragment: Fragment,
)

@FragmentComponent
@Provides
class ForFragmentActivity(
    val fragmentActivity: FragmentActivity,
    val componentActivity: ComponentActivity,
    val activity: Activity,
)

@FragmentComponent
@Provides
class ForFragmentContext(
    @ActivityContext
    val activityContext: Context,
    val applicationContext: Context,
)

@FragmentComponent
@Provides
class ForFragmentCoroutineScope(
    @FragmentCoroutineScope
    val coroutineScope: CoroutineScope,
    @FragmentViewCoroutineScope
    val viewCoroutineScope: CoroutineScope,
)

@FragmentComponent
@Provides
class ForFragmentHolder(
    val forFragment: ForFragment,
    val forFragmentActivity: ForFragmentActivity,
    val forFragmentContext: ForFragmentContext,
)
