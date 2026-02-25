package com.example.registroestudiantes.data.local.mapper

import com.example.registroestudiantes.data.remote.Dto.PlanetDto
import com.example.registroestudiantes.domain.model.Planet

fun PlanetDto.toDomain(): Planet {
    return Planet(
        id = id,
        name = name,
        description = description,
        image = image
    )
}