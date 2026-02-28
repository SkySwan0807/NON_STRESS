package com.example.profedata.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profedata.database.local.entities.TareaEntity
import com.example.profedata.database.repository.TareaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ViewModelTasks @Inject constructor(
    private val repository: TareaRepository
) : ViewModel() {

    val tasks: Flow<List<TareaEntity>> = repository.obtenertodo()


    fun formatDate(timestamp: Long): String {
        val formatter = java.text.SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(java.util.Date(timestamp))
    }

    fun calcularPrioridad(fechaEntrega: Long): String {
        val hoy = System.currentTimeMillis()
        val diferencia = fechaEntrega - hoy
        val dias = diferencia / (1000 * 60 * 60 * 24)

        return when {
            dias <= 1 -> "Alta"
            dias <= 3 -> "Media"
            else -> "Baja"
        }
    }
}
