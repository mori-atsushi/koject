package com.moriatsushi.koject.android.viewmodel

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.internal.Container
import com.moriatsushi.koject.internal.Identifier
import com.moriatsushi.koject.internal.InternalKojectApi

@OptIn(InternalKojectApi::class, ExperimentalKojectApi::class)
class FakeContainer : Container {
    override fun resolve(id: Identifier, componentExtras: ComponentExtras<*>?): Any? {
        return when (id) {
            Identifier.of<TestViewModel>() -> {
                TestViewModel()
            }
            Identifier.of<TestSavedStateViewModel>() -> {
                if (componentExtras is ViewModelComponentExtras) {
                    TestSavedStateViewModel(componentExtras.savedStateHandle)
                } else {
                    null
                }
            }
            else -> null
        }
    }
}
