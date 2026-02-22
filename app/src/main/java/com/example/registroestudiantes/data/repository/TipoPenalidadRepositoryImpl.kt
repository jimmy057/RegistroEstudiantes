package com.example.registroestudiantes.data.repository

import com.example.registroestudiantes.data.local.dao.TipoPenalidadDao
import com.example.registroestudiantes.data.local.mapper.toDomain
import com.example.registroestudiantes.data.local.mapper.toEntity
import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TipoPenalidadRepositoryImpl(
    private val dao: TipoPenalidadDao
) : TipoPenalidadRepository {

    override fun obtenerTodos(): Flow<List<TipoPenalidad>> =
        dao.getAll().map { entidades ->
            entidades.map { it.toDomain() }
        }

    override suspend fun guardar(tipoPenalidad: TipoPenalidad) {
        dao.insert(tipoPenalidad.toEntity())
    }

    override suspend fun obtenerPorId(id: Int): TipoPenalidad? =
        dao.getById(id)?.toDomain()

    override suspend fun eliminar(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun existePorNombre(nombre: String): Boolean =
        dao.exists(nombre) > 0
}