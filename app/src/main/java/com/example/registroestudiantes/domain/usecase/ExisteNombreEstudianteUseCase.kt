package com.example.registroestudiantes.domain.usecase

import com.example.registroestudiantes.domain.repository.EstudianteRepository

class ExisteNombreEstudianteUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(
        nombre: String,
        estudianteId: Int
    ): Boolean {
        return repository.existeNombre(nombre, estudianteId)
    }
}
