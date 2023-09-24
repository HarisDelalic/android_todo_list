package com.delalic.todolistapp.ui.screens.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.R
import com.delalic.todolistapp.components.DisplayAlertDialog
import com.delalic.todolistapp.components.PriorityItem
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.navigation.Action
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
import com.delalic.todolistapp.ui.screens.list.enums.TrailingIconState
import com.delalic.todolistapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.delalic.todolistapp.ui.theme.topAppBarBackgroundColor
import com.delalic.todolistapp.ui.theme.topAppBarContentColor
import com.delalic.todolistapp.ui.viewmodels.SharedViewModel

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED ->
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteAllConfirmed = { sharedViewModel.action.value = Action.DELETE_ALL}
            )

        else -> {
            SearchAppBar(
                text = searchTextState,

                onTextChange = { sharedViewModel.searchTextState.value = it },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    sharedViewModel.getSearchedTasks(searchQuery = it)
                }
            )

        }
    }
}

@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit,
                      onSortClicked: (Priority) -> Unit,
                      onDeleteAllConfirmed: () -> Unit) {
    TopAppBar(
        actions = {
            ListActions(onSearchClicked, onSortClicked, onDeleteAllConfirmed)
        },
        title = { Text(text = "Tasks", color = MaterialTheme.colors.topAppBarContentColor) },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = "Are you sure?",
        message = "Are you sure you want to delete all tasks?",
        openDialog = openDialog,
        onDismissClicked = { openDialog = false },
        onConfirmClicked = { onDeleteAllConfirmed() }
    )

    Row {
        SearchAction(onSearchClicked = onSearchClicked)
        SortAction(onSortClicked = onSortClicked)
        DeleteAllAction(onDeleteAllConfirmed = {
            openDialog = true
        })
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
    onDeleteAllConfirmed: () -> Unit
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
                onDeleteAllConfirmed()
            }) {
                Text(text = "Delete All")
            }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_DELETE) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = "Search", //! transfer to strings.xml
                    color = Color.White,
                    modifier = Modifier
                        .alpha(medium)
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .alpha(ContentAlpha.disabled)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon", //! transfer to strings.xml
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when(trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChange("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE -> {
                                if(text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onTextChange("")
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon", //! transfer to strings.xml
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun PreviewDefaultTopAppBar() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllConfirmed = {})
}

@Preview
@Composable
fun PreviewSearchAppBar() {
    SearchAppBar(text = "search", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
}