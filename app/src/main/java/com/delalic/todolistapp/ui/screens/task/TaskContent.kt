package com.delalic.todolistapp.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.delalic.todolistapp.components.PriorityDropdown
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.ui.theme.LARGE_PADDING
import com.delalic.todolistapp.ui.theme.SMALL_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LARGE_PADDING)
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(bottom = SMALL_PADDING),
            value = title,
            onValueChange = { newTitle -> onTitleChange(newTitle) },
            label = { Text(text = "Title") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        PriorityDropdown(priority = priority, onPrioritySelected = { onPriorityChange(it) } )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = "Description") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = false
        )
    }

}

@Preview
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "some title",
        onTitleChange = {},
        description = "some description",
        onDescriptionChange = {},
        priority = Priority.HIGH,
        onPriorityChange = {}
    )
}