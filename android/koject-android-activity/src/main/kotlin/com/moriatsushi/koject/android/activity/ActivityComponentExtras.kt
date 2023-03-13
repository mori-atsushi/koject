package com.moriatsushi.koject.android.activity

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@PublishedApi
@OptIn(ExperimentalKojectApi::class)
internal class ActivityComponentExtras(
    val componentActivity: ComponentActivity,
) : ComponentExtras<ActivityComponent> {
    val activity: Activity = componentActivity

    @ActivityContext
    val context: Context = activity
}
