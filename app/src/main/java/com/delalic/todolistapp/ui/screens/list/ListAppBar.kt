package com.delalic.todolistapp.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.ui.theme.topAppBarBackgroundColor
import com.delalic.todolistapp.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
    DefaultListAppBar()
}

@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = { Text(text = "Tasks", color = MaterialTheme.colors.topAppBarContentColor)},
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor)
}
@Preview
@Composable
fun PreviewDefaultTopAppBar() {
    ListAppBar()
}