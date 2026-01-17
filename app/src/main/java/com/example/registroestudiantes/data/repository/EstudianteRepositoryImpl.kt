package com.example.registroestudiantes.data.repository

import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.entities.EstudianteEntity
import com.example.registroestudiantes.data.local.mapper.toDomain
import com.example.registroestudiantes.data.local.mapper.toEntity
import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import javax.inject.Inject

class EstudianteRepositoryImpl @Inject constructor(
    private val dao: EstudianteDao
) : EstudianteRepository {

    override suspend fun obtenerTodos(): List<Estudiante> {
        return dao.obtenerTodos().map { it.toDomain() }
    }


    override suspend fun insertar(
        nombres: String,
        email: String,
        edad: Int
    ) {
        dao.insertar(
            EstudianteEntity(
                nombres = nombres,
                email = email,
                edad = edad
            )
        )
    }

    override suspend fun existeNombre(nombre: String): Boolean {
        return dao.existeNombre(nombre) > 0
    }
    override suspend fun eliminar(estudiante: Estudiante) {
        dao.eliminar(estudiante.toEntity())
    }

}
