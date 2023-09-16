package com.delalic.todolistapp.ui.screens.task

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.theme.topAppBarBackgroundColor
import com.delalic.todolistapp.ui.theme.topAppBarContentColor

@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit) {
    NewTaskAppBar(navigateToListScreen)
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {

    TopAppBar(
        navigationIcon = {
            BackIcon(onBackClicked = navigateToListScreen)
        },
        title = {
                Text(text = "Add Task", color = MaterialTheme.colors.topAppBarContentColor)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddTaskIcon(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackIcon(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Arrow Back",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddTaskIcon(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Preview
@Composable
fun PreviewNewTaskAppBar() {
    NewTaskAppBar(navigateToListScreen = {})
}