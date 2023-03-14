package com.moriatsushi.koject.example.kmm.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent

@Provides
@ViewModelComponent
class AndroidTodoListViewModel(
    private val common: TodoListViewModel,
) : ViewModel(), TodoListViewModel by common {
    override fun onCleared() {
        common.onCleared()
    }
}
