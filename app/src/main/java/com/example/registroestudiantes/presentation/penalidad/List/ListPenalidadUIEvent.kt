package com.example.registroestudiantes.presentation.penalidad.List

import com.example.registroestudiantes.domain.model.TipoPenalidad

sealed interface ListPenalidadUIEvent {
    object OnRefresh : ListPenalidadUIEvent
    data class OnEliminar(val penalidad: TipoPenalidad) : ListPenalidadUIEvent
}