package com.delalic.todolistapp.data.repositories

import com.delalic.todolistapp.data.dao.ToDoDao
import com.delalic.todolistapp.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return toDoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(todoTask: ToDoTask) {
        toDoDao.addTask(todoTask = todoTask)
    }

    suspend fun updateTask(todoTask: ToDoTask) {
        toDoDao.updateTask(todoTask = todoTask)
    }

    suspend fun deleteTask(todoTask: ToDoTask) {
        toDoDao.deleteTask(todoTask = todoTask)
    }

    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return toDoDao.searchDatabase(searchQuery = searchQuery)
    }
}