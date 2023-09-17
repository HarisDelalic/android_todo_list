package com.delalic.todolistapp.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.screens.task.TaskScreen
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel
import com.delalic.todolistapp.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListComposable: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(name = Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.get(Constants.TASK_ARGUMENT_KEY)

        sharedViewModel.getSelectedTask(taskId = Integer.parseInt(taskId.toString()))

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            sharedViewModel.updateTaskFields(selectedTask)
        }


        TaskScreen(navigateToListScreen = navigateToListComposable, selectedTask = selectedTask, sharedViewModel = sharedViewModel)
    }
}