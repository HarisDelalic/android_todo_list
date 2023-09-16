package com.delalic.todolistapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.delalic.todolistapp.navigation.destinations.listComposable
import com.delalic.todolistapp.navigation.destinations.taskComposable
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel
import com.delalic.todolistapp.util.Constants

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskComposable = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListComposable = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}