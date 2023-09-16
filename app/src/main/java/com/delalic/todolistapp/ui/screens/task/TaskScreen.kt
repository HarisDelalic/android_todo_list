package com.delalic.todolistapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun TaskScreen() {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = {})
        },
        content = {

        }
    )
}