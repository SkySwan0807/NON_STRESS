package com.example.profedata.database.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tareas",
    foreignKeys = [
        ForeignKey(entity = CursoEntity::class, parentColumns = ["id"], childColumns = ["cursoId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = MateriaEntity::class, parentColumns = ["id"], childColumns = ["materiaId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["cursoId"]), Index(value = ["materiaId"])]
)
data class TareaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cursoId: Int,
    val materiaId: Int,
    val titulo: String,
    val descripcion: String,
    val fechaAsignacion: Long,
    val fechaEntrega: Long,
    val tipo: String,
    val ponderacion: Float
)