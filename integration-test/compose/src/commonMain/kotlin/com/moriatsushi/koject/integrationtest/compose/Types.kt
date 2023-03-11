package com.moriatsushi.koject.integrationtest.compose

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Provides
class CommonClass

@Singleton
@Provides
class CommonSingleton
