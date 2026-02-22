package com.example.registroestudiantes.presentation.penalidad.Edit

data class EditPenalidadUIState(
    val penalidadId: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val puntos: String = "",
    val mensaje: String? = null,
    val error: String? = null
)