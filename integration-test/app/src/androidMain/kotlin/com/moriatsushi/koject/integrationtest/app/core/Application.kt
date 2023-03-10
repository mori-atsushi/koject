package com.moriatsushi.koject.integrationtest.app.core

import android.app.Application
import android.content.Context
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Provides
class ApplicationHolder(
    val application: Application,
    val context: Context,
)

@Singleton
@Provides
class ApplicationSingletonHolder(
    val application: Application,
    val context: Context,
)
