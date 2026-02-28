package data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materias")
data class MateriaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String
)