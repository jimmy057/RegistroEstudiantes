package com.example.registroestudiantes.data.local.mapper

import com.example.registroestudiantes.data.local.entities.AsignaturaEntity
import com.example.registroestudiantes.domain.model.Asignatura

fun AsignaturaEntity.toDomain() = Asignatura(
    asignaturaId = asignaturaId,
    codigo = codigo,
    nombre = nombre,
    aula = aula,
    creditos = creditos
)

fun Asignatura.toEntity() = AsignaturaEntity(
    asignaturaId = asignaturaId,
    codigo = codigo,
    nombre = nombre,
    aula = aula,
    creditos = creditos
)
