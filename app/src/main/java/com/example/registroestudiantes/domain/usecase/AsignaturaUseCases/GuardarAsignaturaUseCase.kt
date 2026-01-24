package com.example.registroestudiantes.domain.usecase.AsignaturaUseCases

import com.example.registroestudiantes.domain.model.Asignatura
import com.example.registroestudiantes.domain.repository.AsignaturaRepository

class GuardarAsignaturaUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(asignatura: Asignatura) {
        repository.guardar(asignatura)
    }
}