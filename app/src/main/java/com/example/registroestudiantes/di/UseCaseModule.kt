package com.example.registroestudiantes.di

import com.example.registroestudiantes.domain.repository.EstudianteRepository
import com.example.registroestudiantes.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideEstudianteUseCases(
        repository: EstudianteRepository
    ): EstudianteUseCases {
        return EstudianteUseCases(
            obtenerEstudiantes = ObtenerEstudiantesUseCase(repository),
            obtenerPorId = ObtenerEstudiantePorIdUseCase(repository),
            guardar = GuardarEstudianteUseCase(repository),
            eliminar = EliminarEstudianteUseCase(repository),
            existeNombre = ExisteNombreEstudianteUseCase(repository)
        )
    }
}