package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import platform.UIKit.UIDevice

@Binds
@Provides
class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
