package com.moriatsushi.koject.android.viewmodel

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner

/**
 * [Reference](https://github.com/androidx/androidx/blob/androidx-main/lifecycle/lifecycle-viewmodel-savedstate/src/androidTest/java/androidx/lifecycle/viewmodel/savedstate/TestComponent.kt)
 */
class TestComponent(
    bundle: Bundle? = null,
) : SavedStateRegistryOwner, LifecycleOwner, ViewModelStoreOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)
    private val savedStateController = SavedStateRegistryController.create(this)

    init {
        savedStateController.performRestore(bundle)
    }

    override val viewModelStore: ViewModelStore = ViewModelStore()
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateController.savedStateRegistry
}
