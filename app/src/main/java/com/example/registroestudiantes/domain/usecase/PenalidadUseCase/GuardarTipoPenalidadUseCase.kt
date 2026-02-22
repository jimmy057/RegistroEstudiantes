package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository

class GuardarTipoPenalidadUseCase(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(tipo: TipoPenalidad) {
        repository.guardar(tipo)
    }
}