package com.moriatsushi.koject.android.activity

import android.app.Activity
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras

@PublishedApi
@OptIn(ExperimentalKojectApi::class)
@ComponentExtras(ActivityComponent::class)
internal class ActivityComponentExtras(
    val componentActivity: ComponentActivity,
) {
    val activity: Activity = componentActivity
}
