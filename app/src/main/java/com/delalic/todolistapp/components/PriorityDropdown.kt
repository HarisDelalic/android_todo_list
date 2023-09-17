package com.delalic.todolistapp.components

import android.graphics.drawable.DrawableContainer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delalic.todolistapp.data.enums.Priority

@Composable
fun PriorityDropdown(
    priority: Priority,
    onPrioritySelected: (Priority) -> (Unit)
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val angle = if (expanded) {
        180f
    } else {
        0f
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(60.dp)
            .clickable(onClick = { expanded = true })
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = ContentAlpha.disabled
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .size(16.dp)
                .weight(weight = 1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            modifier = Modifier.weight(8f),
            style = MaterialTheme.typography.subtitle2
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(degrees = angle)
                .weight(weight = 1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Drop-Down Arrow Icon"
            )
        }
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.fillMaxWidth()) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.LOW)
            }
        ) {
            PriorityItem(priority = Priority.LOW)
        }
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.MEDIUM)
            }
        ) {
            PriorityItem(priority = Priority.MEDIUM)
        }
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.HIGH)
            }
        ) {
            PriorityItem(priority = Priority.HIGH)
        }
    }
}

@Preview
@Composable
fun PriorityDropdownPreview() {
    PriorityDropdown(priority = Priority.LOW, onPrioritySelected = {})
}