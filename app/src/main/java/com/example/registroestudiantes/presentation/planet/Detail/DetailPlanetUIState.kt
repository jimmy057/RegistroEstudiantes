package com.example.registroestudiantes.presentation.planet.Detail

import com.example.registroestudiantes.domain.model.Planet
data class DetailPlanetUIState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)