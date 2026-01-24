package com.example.registroestudiantes.presentation.asignatura.List

import com.example.registroestudiantes.domain.model.Asignatura

sealed interface ListAsignaturaUIEvent {

    data object OnRefresh : ListAsignaturaUIEvent

    data class OnEliminar(
        val asignatura: Asignatura
    ) : ListAsignaturaUIEvent

    data class OnEditar(
        val asignaturaId: Int
    ) : ListAsignaturaUIEvent
}