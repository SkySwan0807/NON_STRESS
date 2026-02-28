package com.example.profedata.database.repository

import com.example.profedata.database.local.dao.MateriaDao
import com.example.profedata.database.local.entities.MateriaEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MateriaRepository @Inject constructor(
    private val materiaDao: MateriaDao
) {

    suspend fun insertar(materia: MateriaEntity) {
        materiaDao.insert(materia)
    }

    suspend fun obtenerTodas(): List<MateriaEntity> {
        return materiaDao.getAll()
    }

    suspend fun obtenerPorId(id: Int): MateriaEntity? {
        return materiaDao.getById(id)
    }
}