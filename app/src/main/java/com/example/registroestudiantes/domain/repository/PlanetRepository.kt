package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.domain.model.Planet

interface PlanetRepository {

    suspend fun getPlanets(): List<Planet>

    suspend fun getPlanetById(id: Int): Planet
}