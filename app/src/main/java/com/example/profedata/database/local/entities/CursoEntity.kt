package data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos")
data class CursoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val grado: String,
    val nivel: String,
    val turno: String,
    val gestion: Int
)