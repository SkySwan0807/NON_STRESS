package data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recursos_educativos",
    foreignKeys = [
        ForeignKey(entity = MateriaEntity::class, parentColumns = ["id"], childColumns = ["materiaId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["materiaId"])]
)
data class RecursoEducativoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val materiaId: Int,
    val titulo: String,
    val descripcion: String,
    val tipo: String,
    val grado: String,
    val nivel: String,
    val azureBlobUrl: String?,
    val verificado: Boolean,
    val fechaSubida: Long
)