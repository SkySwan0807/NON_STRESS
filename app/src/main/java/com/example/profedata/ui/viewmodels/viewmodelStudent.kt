package com.example.profedata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profedata.ui.screens.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor() : ViewModel() {

    // Estado privado que contiene la lista
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    // Estado público que la UI observa
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    init {
        loadStudents()
    }

    private fun loadStudents() {
        // Por ahora cargamos datos de prueba (Mock)
        // En el futuro, aquí llamarás a tu base de datos
        viewModelScope.launch {
            _students.value = listOf(
                Student(1, "Alex Johnson", "10th Grade"),
                Student(2, "Maria Garcia", "11th Grade"),
                Student(3, "Samuel Smith", "10th Grade"),
                Student(4, "Alexandra Diaz", "9th Grade")
            )
        }
    }
}