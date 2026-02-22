package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository

class EliminarTipoPenalidadUseCase(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.eliminar(id)
    }
}
