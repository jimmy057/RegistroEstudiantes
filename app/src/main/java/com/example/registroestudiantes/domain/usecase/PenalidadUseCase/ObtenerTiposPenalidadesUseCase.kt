package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository
import kotlinx.coroutines.flow.Flow

class ObtenerTiposPenalidadesUseCase(
    private val repository: TipoPenalidadRepository
) {
    operator fun invoke(): Flow<List<TipoPenalidad>> {
        return repository.obtenerTodos()
    }
}