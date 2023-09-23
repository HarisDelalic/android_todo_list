package com.delalic.todolistapp.ui.screens.task

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R
import com.delalic.todolistapp.components.DisplayAlertDialog
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.theme.topAppBarBackgroundColor
import com.delalic.todolistapp.ui.theme.topAppBarContentColor

@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit, selectedTask: ToDoTask?)
{
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen)
    }
    else{
        ExistingTaskAppBar(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
    }
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

@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {

    TopAppBar(
        navigationIcon = {
            CloseTaskIcon(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            HandleExistingTaskActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen)
        }
    )
}


@Composable
fun HandleExistingTaskActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {

    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = "Remove ${selectedTask.title}?",
        message = "Are you sure you want to remove ${selectedTask.title}?",
        openDialog = openDialog,
        onDismissClicked = { openDialog = false },
        onConfirmClicked = { navigateToListScreen(Action.DELETE) }
    )

    UpdateTaskIcon(onUpdateClicked = navigateToListScreen)
    DeleteTaskIcon(onDeleteClicked = {
        openDialog = true
    })
}

@Composable
fun CloseTaskIcon(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateTaskIcon(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Update Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteTaskIcon(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteClicked()}) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Preview
@Composable
fun PreviewNewTaskAppBar() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Preview
@Composable
fun PreviewExistingTaskAppBar() {
    ExistingTaskAppBar(selectedTask = ToDoTask(id = 1, title = "My Task", description = "My task Description", priority = Priority.HIGH),
        navigateToListScreen = {})
}