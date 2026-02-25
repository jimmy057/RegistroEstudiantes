package com.example.registroestudiantes.presentation.planet.Detail

sealed interface DetailPlanetUIEvent {

    data class LoadPlanet(val id: Int) : DetailPlanetUIEvent
}