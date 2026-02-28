package com.example.profedata.database.local.dao

import androidx.room.*
import com.example.profedata.database.local.entities.TareaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareaEntity)

    @Query("SELECT * FROM tareas WHERE cursoId = :cursoId AND materiaId = :materiaId")
    fun getTareasByCursoYMateria(cursoId: Int, materiaId: Int): Flow<List<TareaEntity>>

    @Query("SELECT * FROM tareas ORDER BY titulo")
    fun getTareas(): Flow<List<TareaEntity>>

    @Delete
    suspend fun delete(tarea: TareaEntity)
}