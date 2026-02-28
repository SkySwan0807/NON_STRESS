package data.local.dao

import androidx.room.*
import data.local.entities.MateriaEntity

@Dao
interface MateriaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(materia: MateriaEntity)

    @Query("SELECT * FROM materias")
    suspend fun getAll(): List<MateriaEntity>

    @Query("SELECT * FROM materias WHERE id = :id")
    suspend fun getById(id: Int): MateriaEntity?
}