package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.example.kmm.viewmodel.IOSTodoListViewModel
import com.moriatsushi.koject.inject

fun injectTodoListViewModel(): IOSTodoListViewModel {
    return inject()
}
