package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository

class ExisteTipoPenalidadPorNombreUseCase(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(nombre: String): Boolean {
        return repository.existePorNombre(nombre)
    }
}