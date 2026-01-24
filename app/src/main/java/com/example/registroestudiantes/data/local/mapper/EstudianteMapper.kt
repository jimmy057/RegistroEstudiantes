package com.example.registroestudiantes.data.local.mapper

import com.example.registroestudiantes.data.local.entities.EstudianteEntity
import com.example.registroestudiantes.domain.model.Estudiante

fun EstudianteEntity.toDomain(): Estudiante {
    return Estudiante(
        estudianteId = estudianteId,
        nombres = nombres,
        email = email,
        edad = edad
    )
}

fun Estudiante.toEntity(): EstudianteEntity {
    return EstudianteEntity(
        estudianteId = estudianteId,
        nombres = nombres,
        email = email,
        edad = edad
    )
}