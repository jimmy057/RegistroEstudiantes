package com.example.registroestudiantes.domain.repository

import com.example.registroestudiantes.domain.model.Estudiante

interface EstudianteRepository {

    suspend fun obtenerTodos(): List<Estudiante>

    suspend fun insertar(
        nombres: String,
        email: String,
        edad: Int
    )

    suspend fun existeNombre(nombre: String): Boolean
    suspend fun eliminar(estudiante: Estudiante)
}
