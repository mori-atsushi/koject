package com.moriatsushi.koject.android.viewmodel

import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.enableSavedStateHandles
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.internal.InternalKojectApi
import kotlin.test.assertNotNull
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(InternalKojectApi::class)
class KojectViewModelFactoryTest {
    @After
    fun test() {
        Koject.stop()
    }

    @Test
    fun createViewModelWithExtras() {
        Koject._start(FakeContainer())

        val component = TestComponent()
        component.enableSavedStateHandles()
        val extras = component.extras
        val factory = kojectViewModelFactory<TestViewModel>()
        val viewModelProvider = ViewModelProvider(component.viewModelStore, factory, extras)
        val viewModel = viewModelProvider[TestViewModel::class.java]

        assertNotNull(viewModel)
    }

    @Test
    fun createViewModelWithEmptyExtras() {
        Koject._start(FakeContainer())

        val component = TestComponent()
        val extras = MutableCreationExtras()
        val factory = kojectViewModelFactory<TestViewModel>()
        val viewModelProvider = ViewModelProvider(component.viewModelStore, factory, extras)
        val viewModel = viewModelProvider[TestViewModel::class.java]

        assertNotNull(viewModel)
    }

    @Test
    fun createSavedStateViewModelWithExtras() {
        Koject._start(FakeContainer())

        val component = TestComponent()
        component.enableSavedStateHandles()
        val extras = component.extras
        val factory = kojectViewModelFactory<TestSavedStateViewModel>()
        val viewModelProvider = ViewModelProvider(component.viewModelStore, factory, extras)
        val viewModel = viewModelProvider[TestSavedStateViewModel::class.java]

        assertNotNull(viewModel)
    }

    private val TestComponent.extras: MutableCreationExtras
        get() {
            val extras = MutableCreationExtras()
            extras[SAVED_STATE_REGISTRY_OWNER_KEY] = this
            extras[VIEW_MODEL_STORE_OWNER_KEY] = this
            return extras
        }
}
