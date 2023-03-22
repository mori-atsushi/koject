package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.example.kmm.ui.viewmodel.IOSTodoListViewModel
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import com.moriatsushi.koject.test.startTest

object KojectHelper {
    fun start() {
        Koject.start()
    }

    fun startTest() {
        Koject.startTest()
    }

    fun stop() {
        Koject.stop()
    }

    fun injectTodoListViewModel(): IOSTodoListViewModel {
        return inject()
    }
}
