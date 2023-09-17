package com.delalic.todolistapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.data.repositories.ToDoRepository
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
import com.delalic.todolistapp.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val toDoRepository: ToDoRepository): ViewModel() {

    // state: searchAppBarState
    var searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
        private set

    // state: searchTextState
    var searchTextState: MutableState<String> = mutableStateOf("")
        private set

    val taskId: MutableState<Int> = mutableStateOf(0)
    val taskTitle: MutableState<String> = mutableStateOf("")
    val taskDescription: MutableState<String> = mutableStateOf("")
    val taskPriority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)

    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
    fun getAll() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                toDoRepository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success<List<ToDoTask>>(it)
                }
            }
        } catch (e: Throwable) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask = MutableStateFlow<ToDoTask?>(null)
    var selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            toDoRepository.getSelectedTask(taskId).collect {
                task -> _selectedTask.value = task
            }
        }
    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            taskId.value = selectedTask.id
            taskTitle.value = selectedTask.title
            taskDescription.value = selectedTask.description
            taskPriority.value = selectedTask.priority
        } else {
            taskId.value = 0
            taskTitle.value = ""
            taskDescription.value = ""
            taskPriority.value = Priority.LOW
        }
    }
}