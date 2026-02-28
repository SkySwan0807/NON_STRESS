package data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "calificaciones",
    foreignKeys = [
        ForeignKey(entity = TareaEntity::class, parentColumns = ["id"], childColumns = ["tareaId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = EstudianteEntity::class, parentColumns = ["id"], childColumns = ["estudianteId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["tareaId"]), Index(value = ["estudianteId"])]
)
data class CalificacionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tareaId: Int,
    val estudianteId: Int,
    val nota: Float,
    val observacion: String?,
    val fechaCalificacion: Long
)