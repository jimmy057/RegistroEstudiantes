package com.example.registroestudiantes.presentation.planet.Detail

sealed class DetailPlanetUIEvent {

    data class LoadPlanet(val id: Int) : DetailPlanetUIEvent()
}