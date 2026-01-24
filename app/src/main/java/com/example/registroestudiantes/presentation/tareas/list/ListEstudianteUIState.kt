package com.example.registroestudiantes.presentation.tareas.list

import com.example.registroestudiantes.domain.model.Estudiante

data class ListEstudianteUIState(
    val isLoading: Boolean = false,
    val estudiantes: List<Estudiante> = emptyList(),
    val error: String? = null
)