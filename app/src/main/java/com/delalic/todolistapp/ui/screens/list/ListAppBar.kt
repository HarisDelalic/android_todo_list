package com.delalic.todolistapp.ui.screens.list

import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R
import com.delalic.todolistapp.components.PriorityItem
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.ui.theme.topAppBarBackgroundColor
import com.delalic.todolistapp.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllClicked = {}
    )
}

@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit,
                      onSortClicked: (Priority) -> Unit,
                      onDeleteAllClicked: () -> Unit) {
    TopAppBar(
        actions = {
            ListActions(onSearchClicked, onSortClicked, onDeleteAllClicked)
        },
        title = { Text(text = "Tasks", color = MaterialTheme.colors.topAppBarContentColor) },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListActions(onSearchClicked: () -> Unit,
                onSortClicked: (Priority) -> Unit,
                onDeleteAllClicked: () -> Unit) {

    Row {
        SearchAction(onSearchClicked = onSearchClicked)
        SortAction(onSortClicked = onSortClicked)
        DeleteAllAction(onDeleteAllClicked = onDeleteAllClicked)
    }
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.list_search_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.filter_list),
            contentDescription = stringResource(R.string.sort_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                }
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
            ) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
            ) {
                PriorityItem(priority = Priority.NONE)
            }
        }

    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.list_app_bar_more_actions),
            contentDescription = stringResource(R.string.more_actions),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteAllClicked()
            }) {
                Text(text = "Delete All")
            }
        }
    }
}

@Preview
@Composable
fun PreviewDefaultTopAppBar() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllClicked = {})
}