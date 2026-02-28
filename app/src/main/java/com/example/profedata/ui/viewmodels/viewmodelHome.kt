package com.example.profedata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profedata.ui.screens.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = listOf(
                Task(1, "Ensayo sobre la paz", "Historia", "12 Mar", "Alta"),
                Task(2, "Ejercicios de Álgebra", "Matemáticas", "15 Mar", "Media"),
                Task(3, "Lectura: El Quijote", "Literatura", "20 Mar", "Baja"),
                Task(4, "Mapa Conceptual II", "Geografía", "22 Mar", "Media")
            )
        }
    }
}