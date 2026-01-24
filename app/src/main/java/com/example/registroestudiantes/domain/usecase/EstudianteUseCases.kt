package com.example.registroestudiantes.domain.usecase

data class EstudianteUseCases(
    val obtenerEstudiantes: ObtenerEstudiantesUseCase,
    val obtenerPorId: ObtenerEstudiantePorIdUseCase,
    val guardar: GuardarEstudianteUseCase,
    val eliminar: EliminarEstudianteUseCase,
    val existeNombre: ExisteNombreEstudianteUseCase
)