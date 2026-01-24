package com.example.registroestudiantes.domain.usecase.AsignaturaUseCases

import com.example.registroestudiantes.domain.model.Asignatura
import com.example.registroestudiantes.domain.repository.AsignaturaRepository

class ObtenerAsignaturaPorIdUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(id: Int): Asignatura? {
        return repository.obtenerPorId(id)
    }
}