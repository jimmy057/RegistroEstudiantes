package com.example.registroestudiantes.presentation.planet.List

sealed interface ListPlanetUIEvent {

    data object LoadPlanets : ListPlanetUIEvent

    data class OnPlanetClick(val id: Int) : ListPlanetUIEvent
}