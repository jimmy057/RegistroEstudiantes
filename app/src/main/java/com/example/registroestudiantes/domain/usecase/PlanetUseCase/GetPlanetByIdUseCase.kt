package com.example.registroestudiantes.domain.usecase.PlanetUseCase

import com.example.registroestudiantes.domain.repository.PlanetRepository

class GetPlanetByIdUseCase(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPlanetById(id)
}