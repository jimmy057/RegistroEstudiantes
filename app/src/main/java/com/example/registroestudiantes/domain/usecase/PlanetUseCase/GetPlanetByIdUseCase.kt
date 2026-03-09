package com.example.registroestudiantes.domain.usecase.PlanetUseCase

import com.example.registroestudiantes.data.Resource.Resource
import com.example.registroestudiantes.domain.model.Planet
import com.example.registroestudiantes.domain.repository.PlanetRepository

class GetPlanetByIdUseCase(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: Int): Resource<Planet> {
        return repository.getPlanetById(id)
    }
}