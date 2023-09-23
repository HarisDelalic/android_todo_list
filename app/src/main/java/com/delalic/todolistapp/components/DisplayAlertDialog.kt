package com.delalic.todolistapp.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    onDismissClicked: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(text = message, fontSize = MaterialTheme.typography.subtitle1.fontSize, fontWeight = FontWeight.Normal)
            },
            onDismissRequest = { onDismissClicked() },
            dismissButton = {
                OutlinedButton(onClick = { onDismissClicked() }) {
                    Text(text = "No")
                }
            },
            confirmButton = {
                Button(onClick = {
                    onConfirmClicked()
                    onDismissClicked()
                }) {
                    Text(text = "Confirm")
                }
            })
    }
}