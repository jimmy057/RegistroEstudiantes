package com.example.registroestudiantes.domain.usecase.PlanetUseCase

import com.example.registroestudiantes.domain.repository.PlanetRepository

class GetPlanetsUseCase(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke() = repository.getPlanets()
}