package com.example.registroestudiantes.domain.usecase

import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository

class EliminarEstudianteUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(estudiante: Estudiante) {
        repository.eliminar(estudiante)
    }
}