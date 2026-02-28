package com.example.profedata.database.repository

import com.example.profedata.database.local.dao.TareaDao
import com.example.profedata.database.local.entities.TareaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TareaRepository @Inject constructor(
    private val tareaDao: TareaDao
) {

    suspend fun insertar(tarea: TareaEntity) {
        tareaDao.insert(tarea)
    }

    fun obtenerPorCursoYMateria(
        cursoId: Int,
        materiaId: Int
    ): Flow<List<TareaEntity>> {
        return tareaDao.getTareasByCursoYMateria(cursoId, materiaId)
    }

    fun obtenertodo(): Flow<List<TareaEntity>> {
        return tareaDao.getTareas()
    }

    suspend fun eliminar(tarea: TareaEntity) {
        tareaDao.delete(tarea)
    }
}