package com.example.registroestudiantes.presentation.planet.List

sealed class ListPlanetUIEvent {

    object LoadPlanets : ListPlanetUIEvent()

    data class OnPlanetClick(val id: Int) : ListPlanetUIEvent()
}