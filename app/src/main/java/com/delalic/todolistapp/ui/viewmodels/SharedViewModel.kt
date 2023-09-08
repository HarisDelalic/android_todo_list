package com.delalic.todolistapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delalic.todolistapp.data.models.ToDoTask
import com.delalic.todolistapp.data.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val toDoRepository: ToDoRepository): ViewModel() {

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