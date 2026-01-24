package com.example.registroestudiantes.domain.usecase

import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import kotlinx.coroutines.flow.Flow

class ObtenerEstudiantesUseCase(
    private val repository: EstudianteRepository
) {
    operator fun invoke(): Flow<List<Estudiante>> {
        return repository.obtenerTodos()
    }
}