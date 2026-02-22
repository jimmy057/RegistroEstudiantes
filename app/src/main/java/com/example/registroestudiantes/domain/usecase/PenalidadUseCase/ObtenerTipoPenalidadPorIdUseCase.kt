package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository

class ObtenerTipoPenalidadPorIdUseCase(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(id: Int): TipoPenalidad? {
        return repository.obtenerPorId(id)
    }
}