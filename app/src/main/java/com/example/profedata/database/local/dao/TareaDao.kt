package data.local.dao

import androidx.room.*
import data.local.entities.EstudianteEntity

@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareaEntity)

    @Query("SELECT * FROM tareas WHERE cursoId = :cursoId AND materiaId = :materiaId")
    fun getTareasByCursoYMateria(cursoId: Int, materiaId: Int): Flow<List<TareaEntity>>

    @Delete
    suspend fun delete(tarea: TareaEntity)
}