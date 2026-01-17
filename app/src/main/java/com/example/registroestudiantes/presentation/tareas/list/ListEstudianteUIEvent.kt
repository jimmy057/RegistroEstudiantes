package com.example.registroestudiantes.presentation.tareas.list

sealed interface ListEstudianteUIEvent {
    data object OnLoad : ListEstudianteUIEvent
}