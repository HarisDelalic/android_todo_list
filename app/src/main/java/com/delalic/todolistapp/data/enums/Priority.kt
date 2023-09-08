package com.delalic.todolistapp.data.enums

import androidx.compose.ui.graphics.Color
import com.delalic.todolistapp.ui.theme.HighPriorityColor
import com.delalic.todolistapp.ui.theme.LowPriorityColor
import com.delalic.todolistapp.ui.theme.MediumPriorityColor
import com.delalic.todolistapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(color = HighPriorityColor),
    MEDIUM(color = MediumPriorityColor),
    LOW(color = LowPriorityColor),
    NONE(color = NonePriorityColor)
}