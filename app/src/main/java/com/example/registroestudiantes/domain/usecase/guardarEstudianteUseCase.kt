package com.example.registroestudiantes.domain.usecase

import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository

class GuardarEstudianteUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(estudiante: Estudiante): Boolean {

        if (repository.existeNombre(estudiante.nombres)) {
            return false
        }

        repository.insertar(
            nombres = estudiante.nombres,
            email = estudiante.email,
            edad = estudiante.edad
        )
        return true
    }
}
