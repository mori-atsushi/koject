package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.example.kmm.ui.viewmodel.IOSTodoListViewModel
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start
import com.moriatsushi.koject.test.startTest

object KojectHelper {
    fun startKoject() {
        Koject.start()
    }

    fun startKoject(isTesting: Boolean) {
        if (isTesting) {
            Koject.startTest()
        } else {
            Koject.start()
        }
    }

    fun injectTodoListViewModel(): IOSTodoListViewModel {
        return inject()
    }
}
