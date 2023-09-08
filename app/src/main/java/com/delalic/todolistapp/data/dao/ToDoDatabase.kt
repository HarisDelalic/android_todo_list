package com.delalic.todolistapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delalic.todolistapp.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}