package com.delalic.todolistapp.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.delalic.todolistapp.navigation.toAction
import com.delalic.todolistapp.ui.screens.list.ListScreen
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel
import com.delalic.todolistapp.util.Constants
import com.delalic.todolistapp.util.Constants.LIST_ARGUMENT_KEY

fun NavGraphBuilder.listComposable(
    navigateToTaskComposable: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT_KEY) {
            type =  NavType.StringType
        })
    ) { navBackStackEntry: NavBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        ListScreen(navigateToTaskComposable = navigateToTaskComposable, sharedViewModel = sharedViewModel)
    }
}