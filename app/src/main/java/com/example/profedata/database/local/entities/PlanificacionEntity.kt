package data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.profedata.database.local.entities.MateriaEntity

@Entity(
    tableName = "planificaciones",
    foreignKeys = [
        ForeignKey(entity = CursoEntity::class, parentColumns = ["id"], childColumns = ["cursoId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = MateriaEntity::class, parentColumns = ["id"], childColumns = ["materiaId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["cursoId"]), Index(value = ["materiaId"])]
)
data class PlanificacionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cursoId: Int,
    val materiaId: Int,
    val tema: String,
    val duracionMinutos: Int,
    val actividades: String,
    val fechaCreacion: Long
)