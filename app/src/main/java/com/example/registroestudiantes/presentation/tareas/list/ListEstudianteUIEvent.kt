package com.example.registroestudiantes.presentation.tareas.list

import com.example.registroestudiantes.domain.model.Estudiante

sealed interface ListEstudianteUIEvent {
    data object OnLoad : ListEstudianteUIEvent
    data class OnEliminar(val estudiante: Estudiante) : ListEstudianteUIEvent
}