package com.delalic.todolistapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.util.Constants

@Entity(tableName = Constants.DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)