package com.example.registroestudiantes.presentation.asignatura.List

import com.example.registroestudiantes.domain.model.Asignatura

data class ListAsignaturaUIState(
    val asignaturas: List<Asignatura> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)