package com.example.registroestudiantes.presentation.planet.List

import com.example.registroestudiantes.domain.model.Planet

data class ListPlanetUIState(
    val planets: List<Planet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)