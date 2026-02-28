package com.example.profedata.database.local.dao

import androidx.room.*
import data.local.entities.EstudianteEntity

@Dao
interface EstudianteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estudiante: EstudianteEntity)

    @Query("SELECT * FROM estudiantes WHERE cursoId = :cursoId ORDER BY nombre ASC")
    suspend fun getByCurso(cursoId: Int): List<EstudianteEntity>

    @Query("DELETE FROM estudiantes WHERE id = :id")
    suspend fun deleteById(id: Int)
}