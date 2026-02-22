package com.example.registroestudiantes.domain.usecase.PenalidadUseCase

data class PenalidadUseCases(
    val guardar: GuardarTipoPenalidadUseCase,
    val obtenerTodos: ObtenerTiposPenalidadesUseCase,
    val obtenerPorId: ObtenerTipoPenalidadPorIdUseCase,
    val eliminar: EliminarTipoPenalidadUseCase,
    val existePorNombre: ExisteTipoPenalidadPorNombreUseCase
)