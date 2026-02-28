package com.example.profedata.database.repository

import com.example.profedata.database.local.dao.CalificacionDao
import com.example.profedata.database.local.entities.CalificacionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalificacionRepository @Inject constructor(
    private val calificacionDao: CalificacionDao
) {

    suspend fun insertarCalificacion(calificacion: CalificacionEntity) {
        calificacionDao.insert(calificacion)
    }

    fun obtenerNotasPorTarea(tareaId: Int): Flow<List<CalificacionEntity>> {
        return calificacionDao.getNotasPorTarea(tareaId)
    }

    fun obtenerNotasPorEstudiante(estudianteId: Int): Flow<List<CalificacionEntity>> {
        return calificacionDao.getNotasPorEstudiante(estudianteId)
    }
}