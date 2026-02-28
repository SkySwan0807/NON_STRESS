package com.example.profedata.database.repository

import com.example.profedata.database.local.dao.CursoDao
import data.local.entities.CursoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CursoRepository @Inject constructor(
    private val cursoDao: CursoDao
) {

    suspend fun insertarCurso(curso: CursoEntity) {
        cursoDao.insert(curso)
    }

    fun obtenerTodosLosCursos(): Flow<List<CursoEntity>> {
        return cursoDao.getAllCursos()
    }
}