package com.moriatsushi.koject.integrationtest.android.activity

import android.app.Activity
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.activity.ActivityComponent

@ActivityComponent
@Provides
class ForActivity(
    val componentActivity: ComponentActivity,
    val activity: Activity,
)

@ActivityComponent
@Provides
class ForActivityHolder(
    val forActivity: ForActivity,
)
