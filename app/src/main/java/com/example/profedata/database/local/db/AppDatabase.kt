package com.example.profedata.database.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.profedata.database.local.dao.CalificacionDao
import com.example.profedata.database.local.dao.CursoDao
import com.example.profedata.database.local.dao.EstudianteDao
import com.example.profedata.database.local.dao.MateriaDao
import com.example.profedata.database.local.dao.TareaDao
import com.example.profedata.database.local.entities.MateriaEntity
import data.local.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    //abstract fun planificacionDao(): PlanificacionDao
    //abstract fun recursoEducativoDao(): RecursoEducativoDao
    abstract fun tareaDao(): TareaDao
    abstract fun calificacionDao(): CalificacionDao
    abstract fun cursoDao(): CursoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "profedata_db"
                )
                    .fallbackToDestructiveMigration() // Esto borra y recrea la BD
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se ejecuta cuando la BD se crea por primera vez
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    prepopulate(database)
                                }
                            }
                        }

                    })
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private suspend fun prepopulate(db: AppDatabase) {
            println("📦 PREPOBLANDO BASE DE DATOS...")
            insertEssentialData(db)
        }

        private suspend fun insertEssentialData(db: AppDatabase) {
            try {

                println("📦 Insertando datos esenciales de PRIMARIA...")

                val materiaDao = db.materiaDao()
                val cursoDao = db.cursoDao()
                val estudianteDao = db.estudianteDao()
                val tareaDao = db.tareaDao()

                val gestionActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
                val fechaActual = System.currentTimeMillis()

                // 📘 Materias Primaria
                val materiasPrimaria = listOf(
                    MateriaEntity(nombre = "Matemática", descripcion = "Números y problemas"),
                    MateriaEntity(nombre = "Lenguaje y Comunicación", descripcion = "Lectura y escritura"),
                    MateriaEntity(nombre = "Ciencias Naturales", descripcion = "Entorno natural"),
                    MateriaEntity(nombre = "Ciencias Sociales", descripcion = "Historia básica"),
                    MateriaEntity(nombre = "Educación Física", descripcion = "Actividad física"),
                    MateriaEntity(nombre = "Artes Plásticas", descripcion = "Creatividad"),
                    MateriaEntity(nombre = "Música", descripcion = "Expresión musical"),
                    MateriaEntity(nombre = "Valores", descripcion = "Convivencia")
                )

                materiasPrimaria.forEach { materiaDao.insert(it) }
                println("✅ Materias insertadas")

                // 🏫 Cursos 1° a 6° Primaria
                val cursosPrimaria = (1..6).map { grado ->
                    CursoEntity(
                        nombre = "${grado}° Primaria",
                        grado = grado.toString(),
                        nivel = "Primaria",
                        turno = "Mañana",
                        gestion = gestionActual
                    )
                }

                cursosPrimaria.forEach { cursoDao.insert(it) }
                println("✅ Cursos insertados")

                // ⚠ IMPORTANTE:
                // Como los IDs son autoGenerate, los asumimos en orden de inserción
                // (1..6 para cursos, 1..8 para materias en BD nueva)

                // 👨‍🎓 Estudiantes de ejemplo (5 por curso)
                for (cursoId in 1..6) {
                    for (i in 1..5) {
                        estudianteDao.insert(
                            EstudianteEntity(
                                nombre = "Estudiante $i - Curso $cursoId",
                                cursoId = cursoId,
                                fechaRegistro = fechaActual
                            )
                        )
                    }
                }

                println("✅ Estudiantes insertados")

                // 📚 Tareas de ejemplo (2 por curso en Matemática)
                val ahora = System.currentTimeMillis()
                val unaSemana = 7 * 24 * 60 * 60 * 1000L

                for (cursoId in 1..6) {
                    tareaDao.insert(
                        TareaEntity(
                            cursoId = cursoId,
                            materiaId = 1, // Matemática
                            titulo = "Ejercicios de suma",
                            descripcion = "Resolver página 12",
                            fechaAsignacion = ahora,
                            fechaEntrega = ahora + unaSemana,
                            tipo = "Tarea",
                            ponderacion = 20f
                        )
                    )

                    tareaDao.insert(
                        TareaEntity(
                            cursoId = cursoId,
                            materiaId = 1,
                            titulo = "Problemas aplicados",
                            descripcion = "Resolver problemas prácticos",
                            fechaAsignacion = ahora,
                            fechaEntrega = ahora + unaSemana,
                            tipo = "Evaluación",
                            ponderacion = 30f
                        )
                    )
                }

                println("✅ Tareas insertadas")

                println("🎉 Base de datos pre-poblada correctamente")

            } catch (e: Exception) {
                println("❌ ERROR en prepoblación: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}