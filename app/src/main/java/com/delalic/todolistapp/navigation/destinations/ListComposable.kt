package com.delalic.todolistapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.delalic.todolistapp.ui.screens.list.ListScreen
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel
import com.delalic.todolistapp.util.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskComposable: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT_KEY) {
            type =  NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskComposable = navigateToTaskComposable, sharedViewModel = sharedViewModel)
    }
}