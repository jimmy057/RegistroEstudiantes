package com.example.registroestudiantes.domain.usecase

import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository

class ObtenerEstudiantePorIdUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(id: Int): Estudiante? {
        return repository.obtenerPorId(id)
    }
}