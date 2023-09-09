package com.delalic.todolistapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListComposable: (Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(name = Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}