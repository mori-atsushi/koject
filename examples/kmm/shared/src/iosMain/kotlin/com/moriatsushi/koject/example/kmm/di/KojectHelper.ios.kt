package com.moriatsushi.koject.example.kmm.di

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.example.kmm.viewmodel.IOSTodoListViewModel
import com.moriatsushi.koject.inject
import com.moriatsushi.koject.start

fun startKoject() {
    Koject.start()
}

fun injectTodoListViewModel(): IOSTodoListViewModel {
    return inject()
}
