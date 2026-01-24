package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.domain.model.Asignatura
import kotlinx.coroutines.flow.Flow

interface AsignaturaRepository {
    fun obtenerTodas(): Flow<List<Asignatura>>
    suspend fun obtenerPorId(id: Int): Asignatura?
    suspend fun guardar(asignatura: Asignatura)
    suspend fun eliminar(asignatura: Asignatura)
    suspend fun existeNombre(nombre: String): Boolean
}
