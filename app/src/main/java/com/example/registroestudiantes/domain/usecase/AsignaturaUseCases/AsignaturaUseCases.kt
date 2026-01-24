package com.example.registroestudiantes.domain.usecase.AsignaturaUseCases

data class AsignaturaUseCases(
    val obtenerTodas: ObtenerAsignaturasUseCase,
    val obtenerPorId: ObtenerAsignaturaPorIdUseCase,
    val guardar: GuardarAsignaturaUseCase,
    val eliminar: EliminarAsignaturaUseCase
)