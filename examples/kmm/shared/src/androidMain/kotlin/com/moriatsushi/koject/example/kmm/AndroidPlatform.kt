package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides

@Binds
@Provides
class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}
