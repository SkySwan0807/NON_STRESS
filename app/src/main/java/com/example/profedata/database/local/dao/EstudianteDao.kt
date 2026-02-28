package com.example.profedata.database.local.dao

import androidx.room.*
import com.example.profedata.database.local.entities.EstudianteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EstudianteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estudiante: EstudianteEntity)

    @Update
    suspend fun update(estudiante: EstudianteEntity)

    @Delete
    suspend fun delete(estudiante: EstudianteEntity)

    @Query("DELETE FROM estudiantes WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM estudiantes WHERE cursoId = :cursoId ORDER BY nombre ASC")
    fun getByCurso(cursoId: Int): Flow<List<EstudianteEntity>>

    @Query("SELECT * FROM estudiantes ORDER BY nombre ASC")
    fun getAll(): Flow<List<EstudianteEntity>>

    @Query("SELECT * FROM estudiantes WHERE id = :id")
    suspend fun getById(id: Int): EstudianteEntity?
}