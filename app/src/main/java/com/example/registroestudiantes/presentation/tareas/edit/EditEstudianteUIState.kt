package com.example.registroestudiantes.presentation.tareas.edit

data class EditEstudianteUIState(
    val estudianteId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val edad: String = "",
    val mensaje: String? = null
)


