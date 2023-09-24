package com.delalic.todolistapp.ui.screens.list

import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
import com.delalic.todolistapp.ui.theme.LARGE_PADDING
import com.delalic.todolistapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.delalic.todolistapp.ui.theme.taskItemBackgroundColor
import com.delalic.todolistapp.ui.theme.taskItemContentColor
import com.delalic.todolistapp.util.RequestState

@Composable
fun ListContent(tasks: RequestState<List<ToDoTask>>,
                searchedTasks: RequestState<List<ToDoTask>>,
                searchAppBarState: SearchAppBarState,
                lowPriorityTasks: List<ToDoTask>,
                highPriorityTasks: List<ToDoTask>,
                sortState: RequestState<Priority>,
                navigateToTaskScreen: (taskId: Int) -> Unit) {


    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    handleTasks(
                        tasks = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.NONE -> {
                if (tasks is RequestState.Success) {
                    handleTasks(
                        tasks = tasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.LOW -> {
                handleTasks(
                    tasks = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
            sortState.data == Priority.HIGH -> {
                handleTasks(
                    tasks = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
    }
}

@Composable
fun handleTasks(
//    could be searchedTasks or allTasks
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn {
            items(items = tasks, key = { task ->
                task.id
            }) { task: ToDoTask ->
                TaskItem(
                    toDoTask = task,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = 4.dp,
        onClick = { navigateToTaskScreen(toDoTask.id) },
        color = MaterialTheme.colors.taskItemBackgroundColor
    )
    {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemContentColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemContentColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(1, "Title", "description", Priority.HIGH),
        navigateToTaskScreen = {}
    )
}