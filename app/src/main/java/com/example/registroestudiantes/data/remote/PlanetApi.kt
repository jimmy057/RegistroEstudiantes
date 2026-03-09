package com.example.registroestudiantes.data.remote

import com.example.registroestudiantes.data.remote.Dto.PlanetDto
import com.example.registroestudiantes.data.remote.Dto.PlanetResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {

    @GET("planets")
    suspend fun getPlanets(): Response<PlanetResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetById(
        @Path("id") id: Int
    ): Response<PlanetDto>
}