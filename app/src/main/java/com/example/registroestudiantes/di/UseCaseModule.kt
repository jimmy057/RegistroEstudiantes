package com.example.registroestudiantes.di

import com.example.registroestudiantes.domain.repository.AsignaturaRepository
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import com.example.registroestudiantes.domain.usecase.*
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.AsignaturaUseCases
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.EliminarAsignaturaUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.GuardarAsignaturaUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.ObtenerAsignaturaPorIdUseCase
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.ObtenerAsignaturasUseCase
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.EliminarTipoPenalidadUseCase
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.ExisteTipoPenalidadPorNombreUseCase
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.GuardarTipoPenalidadUseCase
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.ObtenerTipoPenalidadPorIdUseCase
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.ObtenerTiposPenalidadesUseCase
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.PenalidadUseCases
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

@Module
@InstallIn(SingletonComponent::class)
object PenalidadUseCaseModule {

    @Provides
    @Singleton
    fun providePenalidadUseCases(
        repository: TipoPenalidadRepository
    ): PenalidadUseCases {
        return PenalidadUseCases(
            obtenerTodos = ObtenerTiposPenalidadesUseCase(repository),
            obtenerPorId = ObtenerTipoPenalidadPorIdUseCase(repository),
            guardar = GuardarTipoPenalidadUseCase(repository),
            eliminar = EliminarTipoPenalidadUseCase(repository),
            existePorNombre = ExisteTipoPenalidadPorNombreUseCase(repository)
        )
    }
}