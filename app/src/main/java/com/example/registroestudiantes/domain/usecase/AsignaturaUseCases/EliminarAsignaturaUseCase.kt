package com.example.registroestudiantes.domain.usecase.AsignaturaUseCases

import com.example.registroestudiantes.domain.model.Asignatura
import com.example.registroestudiantes.domain.repository.AsignaturaRepository

class EliminarAsignaturaUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(asignatura: Asignatura) {
        repository.eliminar(asignatura)
    }
}