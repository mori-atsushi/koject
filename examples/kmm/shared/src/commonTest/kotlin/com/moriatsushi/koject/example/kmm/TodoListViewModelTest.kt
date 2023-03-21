package com.moriatsushi.koject.example.kmm

import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.example.kmm.ui.viewmodel.TodoListViewModel
import com.moriatsushi.koject.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.isActive

class TodoListViewModelTest {
    @Test
    fun addTask() = Koject.runTest {
        val subject = inject<TodoListViewModel>()
        assertEquals(0, subject.list.value.value.size)
        subject.addTask("sample title")
        assertEquals(1, subject.list.value.value.size)
    }

    @Test
    fun changeComplete() = Koject.runTest {
        val subject = inject<TodoListViewModel>()
        subject.addTask("sample title")
        val task = subject.list.value.value.first()
        assertFalse(task.isCompleted)

        subject.changeComplete(task, true)
        val actual = subject.list.value.value.first()
        assertTrue(actual.isCompleted)
    }

    @Test
    fun onCleared() = Koject.runTest {
        val subject = inject<TodoListViewModel>()
        assertTrue(subject.coroutineScope.isActive)

        subject.onCleared()
        assertFalse(subject.coroutineScope.isActive)
    }
}
