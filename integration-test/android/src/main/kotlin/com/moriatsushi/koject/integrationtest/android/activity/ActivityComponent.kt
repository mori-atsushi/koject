package com.moriatsushi.koject.integrationtest.android.activity

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.activity.ActivityComponent
import com.moriatsushi.koject.android.activity.ActivityContext
import com.moriatsushi.koject.android.activity.ActivityCoroutineScope
import kotlinx.coroutines.CoroutineScope

@ActivityComponent
@Provides
class ForActivity(
    val componentActivity: ComponentActivity,
    val activity: Activity,
    @ActivityContext
    val activityContext: Context,
    val applicationContext: Context,
)

@ActivityComponent
@Provides
class ForActivityWithCoroutineScope(
    @ActivityCoroutineScope
    val coroutineScope: CoroutineScope,
)

@ActivityComponent
@Provides
class ForActivityHolder(
    val forActivity: ForActivity,
)
