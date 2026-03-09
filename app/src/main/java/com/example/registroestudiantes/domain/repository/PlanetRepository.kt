package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.data.Resource.Resource
import com.example.registroestudiantes.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanets(): Resource<List<Planet>>
    suspend fun getPlanetById(id: Int): Resource<Planet>
}