package com.example.registroestudiantes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registroestudiantes.data.local.entities.EstudianteEntity

@Dao
interface EstudianteDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(estudiante: EstudianteEntity)

    @Query("SELECT * FROM estudiantes")
    suspend fun obtenerTodos(): List<EstudianteEntity>

    @Query("SELECT COUNT(*) FROM estudiantes WHERE nombres = :nombre")
    suspend fun existeNombre(nombre: String): Int

    @Delete
    suspend fun eliminar(estudiante: EstudianteEntity)
}

