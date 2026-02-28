package com.example.profedata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profedata.database.repository.EstudianteRepository
import com.example.profedata.ui.screens.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(
    private val repository: EstudianteRepository
) : ViewModel() {

    val students: StateFlow<List<Student>> =
        repository.obtenerTodos()
            .map { entities ->
                entities.map { entity ->
                    Student(
                        id = entity.id,
                        name = entity.nombre,
                        grade = "Curso ${entity.cursoId}"
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )


    fun eliminarEstudiante(id: Int) {
        viewModelScope.launch {
            repository.eliminarPorId(id)
        }
    }
}