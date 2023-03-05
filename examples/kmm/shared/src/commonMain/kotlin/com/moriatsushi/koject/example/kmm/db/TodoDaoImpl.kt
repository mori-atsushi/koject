package com.moriatsushi.koject.example.kmm.db

import com.moriatsushi.koject.Binds
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.example.kmm.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Provides
@Singleton
@Binds
internal class TodoDaoImpl(
    private val db: Database,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : TodoDao {
    override suspend fun fetchAll(): List<TodoTask> {
        return withContext(ioDispatcher) {
            db.todoQueries.selectAllTodoTasks().executeAsList()
        }
    }

    override suspend fun insertTodoTask(
        id: String,
        title: String,
        isCompleted: Boolean,
    ) {
        withContext(ioDispatcher) {
            db.todoQueries.insertTodoTask(id, title, isCompleted)
        }
    }

    override suspend fun updateTodoTask(
        id: String,
        title: String,
        isCompleted: Boolean,
    ) {
        withContext(ioDispatcher) {
            db.todoQueries.updateTodoTask(title, isCompleted, id)
        }
    }
}
