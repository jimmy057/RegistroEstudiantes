package com.example.registroestudiantes.presentation.planet.List

import com.example.registroestudiantes.domain.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null
)