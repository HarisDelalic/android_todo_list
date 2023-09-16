package com.delalic.todolistapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.delalic.todolistapp.navigation.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = {

        }
    )
}