package com.example.registroestudiantes.data.local.mapper

import com.example.registroestudiantes.data.local.entities.EstudianteEntity
import com.example.registroestudiantes.domain.model.Estudiante

fun EstudianteEntity.toDomain(): Estudiante {
    return Estudiante(
        estudianteId = this.estudianteId,
        nombres = this.nombres,
        email = this.email,
        edad = this.edad
    )
}

fun Estudiante.toEntity(): EstudianteEntity {
    return EstudianteEntity(
        estudianteId = this.estudianteId,
        nombres = this.nombres,
        email = this.email,
        edad = this.edad
    )
}