package com.delalic.todolistapp.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R

@Composable
fun ListScreen(navigateToTaskComposable: (Int) -> Unit) {
    Scaffold(
        topBar = { DefaultListAppBar() },
        content = {},
        floatingActionButton = {
            FAB(navigateToTaskComposable = navigateToTaskComposable)
        }
    )

}

@Composable
fun FAB(navigateToTaskComposable: (Int) -> Unit) {
    FloatingActionButton(onClick = { navigateToTaskComposable(-1) }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_task_button_text), tint = Color.White)
    }
}

@Preview
@Composable
private fun Preview() {
    ListScreen(navigateToTaskComposable = {})
}