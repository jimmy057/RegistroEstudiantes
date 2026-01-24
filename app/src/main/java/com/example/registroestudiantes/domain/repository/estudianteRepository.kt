package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.domain.model.Estudiante
import kotlinx.coroutines.flow.Flow

interface EstudianteRepository {

    fun obtenerTodos(): Flow<List<Estudiante>>

    suspend fun obtenerPorId(id: Int): Estudiante?

    suspend fun guardar(estudiante: Estudiante)

    suspend fun eliminar(estudiante: Estudiante)

    suspend fun existeNombre(
        nombre: String,
        estudianteId: Int
    ): Boolean

}
