package data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.dao.*
import data.local.entities.*

@Database(
    entities = [
        MateriaEntity::class,
        CursoEntity::class,
        EstudianteEntity::class,
        PlanificacionEntity::class,
        RecursoEducativoEntity::class,
        TareaEntity::class,
        CalificacionEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun materiaDao(): MateriaDao
    abstract fun estudianteDao(): EstudianteDao
    abstract fun planificacionDao(): PlanificacionDao
    abstract fun recursoEducativoDao(): RecursoEducativoDao
    abstract fun tareaDao(): TareaDao
    abstract fun calificacionDao(): CalificacionDao
}