package com.example.profedata.database.local.dao

import androidx.room.*
import data.local.entities.CalificacionEntity
import data.local.entities.CursoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(curso: CursoEntity)


    // Obtener todos los cursos
    @Query("SELECT * FROM cursos ORDER BY grado ASC")
    fun getAllCursos(): Flow<List<CursoEntity>>

}