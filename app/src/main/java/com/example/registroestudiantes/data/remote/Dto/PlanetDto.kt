package com.example.registroestudiantes.data.remote.Dto

import com.example.registroestudiantes.domain.model.Planet

data class PlanetDto(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
) {
    fun toDomain(): Planet {
        return Planet(
            id = id,
            name = name,
            description = description,
            image = image
        )
    }
}