package com.delalic.todolistapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.taskTitle
    val description: String by sharedViewModel.taskDescription
    val priority: Priority by sharedViewModel.taskPriority

    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                description = description,
                onDescriptionChange = { sharedViewModel.taskDescription.value = it },
                priority = priority,
                onPriorityChange = { sharedViewModel.taskPriority.value = it }
            )
        }
    )
}