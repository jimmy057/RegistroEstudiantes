package com.example.registroestudiantes.data.repository

import com.example.registroestudiantes.data.local.mapper.toDomain
import com.example.registroestudiantes.data.remote.PlanetApiService
import com.example.registroestudiantes.domain.model.Planet
import com.example.registroestudiantes.domain.repository.PlanetRepository

class PlanetRepositoryImpl(
    private val api: PlanetApiService
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return api.getPlanets().items.map { it.toDomain() }
    }

    override suspend fun getPlanetById(id: Int): Planet {
        return api.getPlanetById(id).toDomain()
    }
}