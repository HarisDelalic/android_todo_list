package com.delalic.todolistapp.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel

@Composable
fun ListScreen(navigateToTaskComposable: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel) {
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAll()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState(initial = emptyList())

    Scaffold(
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
