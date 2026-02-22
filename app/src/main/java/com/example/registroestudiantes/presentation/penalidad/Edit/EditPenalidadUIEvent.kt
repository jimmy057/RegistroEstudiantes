package com.example.registroestudiantes.presentation.penalidad.Edit

sealed class EditPenalidadUIEvent {
    data class OnNombreChange(val value: String) : EditPenalidadUIEvent()
    data class OnDescripcionChange(val value: String) : EditPenalidadUIEvent()
    data class OnPuntosChange(val value: String) : EditPenalidadUIEvent()
    object OnGuardar : EditPenalidadUIEvent()
}