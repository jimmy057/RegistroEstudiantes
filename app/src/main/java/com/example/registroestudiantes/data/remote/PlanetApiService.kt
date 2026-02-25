package com.example.registroestudiantes.data.remote

import com.example.registroestudiantes.data.remote.Dto.PlanetDto
import com.example.registroestudiantes.data.remote.Dto.PlanetResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApiService {

    @GET("api/planets")
    suspend fun getPlanets(): PlanetResponseDto

    @GET("api/planets/{id}")
    suspend fun getPlanetById(
        @Path("id") id: Int
    ): PlanetDto
}