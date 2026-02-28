package data.local.dao

import androidx.room.*
import data.local.entities.EstudianteEntity

@Dao
interface CalificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calificacion: CalificacionEntity)

    // Obtiene las notas de una tarea específica para todos los alumnos
    @Query("SELECT * FROM calificaciones WHERE tareaId = :tareaId")
    fun getNotasPorTarea(tareaId: Int): Flow<List<CalificacionEntity>>

    // Para el análisis de IA: Obtiene todas las notas de un estudiante
    @Query("SELECT * FROM calificaciones WHERE estudianteId = :estudianteId")
    fun getNotasPorEstudiante(estudianteId: Int): Flow<List<CalificacionEntity>>
}