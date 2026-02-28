package com.example.profedata.database.repository

import com.example.profedata.database.local.dao.EstudianteDao
import com.example.profedata.database.local.entities.EstudianteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EstudianteRepository @Inject constructor(
    private val estudianteDao: EstudianteDao
) {

    suspend fun insertar(estudiante: EstudianteEntity) {
        estudianteDao.insert(estudiante)
    }

    suspend fun actualizar(estudiante: EstudianteEntity) {
        estudianteDao.update(estudiante)
    }

    suspend fun eliminar(estudiante: EstudianteEntity) {
        estudianteDao.delete(estudiante)
    }

    suspend fun eliminarPorId(id: Int) {
        estudianteDao.deleteById(id)
    }

    fun obtenerPorCurso(cursoId: Int): Flow<List<EstudianteEntity>> {
        return estudianteDao.getByCurso(cursoId)
    }

    fun obtenerTodos(): Flow<List<EstudianteEntity>> {
        return estudianteDao.getAll()
    }

    suspend fun obtenerPorId(id: Int): EstudianteEntity? {
        return estudianteDao.getById(id)
    }
}