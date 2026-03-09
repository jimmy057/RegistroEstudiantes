package com.example.registroestudiantes.data.repository

import com.example.registroestudiantes.data.Resource.Resource
import com.example.registroestudiantes.data.local.mapper.toDomain
import com.example.registroestudiantes.data.remote.PlanetApi
import com.example.registroestudiantes.domain.model.Planet
import com.example.registroestudiantes.domain.repository.PlanetRepository
import javax.inject.Inject

class PlanetRepositoryImpl(
    private val api: PlanetApi
) : PlanetRepository {

    override suspend fun getPlanets(): Resource<List<Planet>> {

        return try {

            val response = api.getPlanets()

            if (response.isSuccessful && response.body() != null) {

                val planets = response.body()!!.items.map {
                    it.toDomain()
                }

                Resource.Success(planets)

            } else {
                Resource.Error("Error servidor")
            }

        } catch (e: Exception) {
            Resource.Error("Error conexión")
        }
    }

    override suspend fun getPlanetById(id: Int): Resource<Planet> {

        return try {

            val response = api.getPlanetById(id)

            if (response.isSuccessful && response.body() != null) {

                Resource.Success(response.body()!!.toDomain())

            } else {
                Resource.Error("No encontrado")
            }

        } catch (e: Exception) {
            Resource.Error("Error conexión")
        }
    }
}