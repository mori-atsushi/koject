package com.moriatsushi.koject.example.kmm.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides

@Provides
class AndroidTodoListViewModel(
    private val common: TodoListViewModel,
) : ViewModel(), TodoListViewModel by common {
    override fun onCleared() {
        common.onCleared()
    }
}
