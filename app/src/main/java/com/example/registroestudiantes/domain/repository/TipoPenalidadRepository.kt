package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.domain.model.TipoPenalidad
import kotlinx.coroutines.flow.Flow

interface TipoPenalidadRepository {

    suspend fun guardar(tipoPenalidad: TipoPenalidad)

    fun obtenerTodos(): Flow<List<TipoPenalidad>>

    suspend fun obtenerPorId(id: Int): TipoPenalidad?

    suspend fun eliminar(id: Int)

    suspend fun existePorNombre(nombre: String): Boolean
}