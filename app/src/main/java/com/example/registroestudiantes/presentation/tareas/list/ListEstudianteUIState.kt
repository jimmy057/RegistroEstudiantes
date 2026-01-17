package com.example.registroestudiantes.presentation.tareas.list

import com.example.registroestudiantes.domain.model.Estudiante

data class ListEstudianteUIState(
    val estudiantes: List<Estudiante> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)