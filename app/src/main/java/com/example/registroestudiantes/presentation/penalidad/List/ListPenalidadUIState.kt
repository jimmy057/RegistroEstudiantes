package com.example.registroestudiantes.presentation.penalidad.List

import com.example.registroestudiantes.domain.model.TipoPenalidad

data class ListPenalidadUIState(
    val penalidades: List<TipoPenalidad> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)