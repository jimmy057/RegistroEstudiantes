package com.example.registroestudiantes.domain.usecase.AsignaturaUseCases

import com.example.registroestudiantes.domain.model.Asignatura
import com.example.registroestudiantes.domain.repository.AsignaturaRepository
import kotlinx.coroutines.flow.Flow

class ObtenerAsignaturasUseCase(
    private val repository: AsignaturaRepository
) {
    operator fun invoke(): Flow<List<Asignatura>> {
        return repository.obtenerTodas()
    }
}