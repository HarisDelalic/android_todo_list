package com.delalic.todolistapp.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.launch

@Composable
fun ListScreen(navigateToTaskComposable: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel) {
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val action by sharedViewModel.action

    val scaffoldState = rememberScaffoldState();

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        action = action,
        taskTitle = sharedViewModel.taskTitle.value,
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        handleDatabaseAction = { sharedViewModel.handleDatabaseAction(action = action) }
    )

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAll()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ListAppBar(
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState
        ) },
        content = {
            ListContent(
                tasks = allTasks,
                navigateToTask = navigateToTaskComposable
            )
        },
        floatingActionButton = {
            FAB(navigateToTaskComposable = navigateToTaskComposable)
        }
    )

}

@Composable
fun FAB(navigateToTaskComposable: (taskId: Int) -> Unit) {
    FloatingActionButton(onClick = { navigateToTaskComposable(-1) }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_task_button_text), tint = Color.White)
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    action: Action,
    taskTitle: String,
    onUndoClicked: (Action) -> Unit,
    handleDatabaseAction: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            handleDatabaseAction()

            scope.launch {
                val snackBarResults = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = actionLabel(action)
                )
                undoDeleteTask(snackBarResults, onUndoClicked, action)
            }
        }
    }
}

private fun actionLabel(action: Action) : String {
    return if(action == Action.DELETE) {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeleteTask(
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit,
    action: Action
) : Unit {
    if(snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}