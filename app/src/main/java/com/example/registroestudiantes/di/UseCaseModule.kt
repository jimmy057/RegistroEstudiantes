package com.example.registroestudiantes.di

import com.example.registroestudiantes.domain.repository.AsignaturaRepository
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import com.example.registroestudiantes.domain.usecase.*
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.AsignaturaUseCases
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.EliminarAsignaturaUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.GuardarAsignaturaUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.ObtenerAsignaturaPorIdUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.ObtenerAsignaturasUseCase
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
        return EstudianteUseCases(repository)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object AsignaturaUseCaseModule {

    @Provides
    @Singleton
    fun provideAsignaturaUseCases(
        repository: AsignaturaRepository
    ): AsignaturaUseCases {
        return AsignaturaUseCases(
            obtenerTodas = ObtenerAsignaturasUseCase(repository),
            obtenerPorId = ObtenerAsignaturaPorIdUseCase(repository),
            guardar = GuardarAsignaturaUseCase(repository),
            eliminar = EliminarAsignaturaUseCase(repository)
        )
    }
}