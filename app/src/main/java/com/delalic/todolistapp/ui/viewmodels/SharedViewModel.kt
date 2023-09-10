package com.delalic.todolistapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.data.repositories.ToDoRepository
import com.delalic.todolistapp.ui.screens.list.enums.SearchAppBarState
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

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())

    val allTasks: StateFlow<List<ToDoTask>> = _allTasks
    fun getAll() {
        viewModelScope.launch {
            toDoRepository.getAllTasks.collect {
                _allTasks.value = it
            }
        }
    }
}