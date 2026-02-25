package com.example.registroestudiantes.di

import android.content.Context
import androidx.room.Room
import com.example.registroestudiantes.data.local.dao.AsignaturaDao
import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.dao.TipoPenalidadDao
import com.example.registroestudiantes.data.local.databases.AppDatabase
import com.example.registroestudiantes.data.repository.AsignaturaRepositoryImpl
import com.example.registroestudiantes.data.repository.EstudianteRepositoryImpl
import com.example.registroestudiantes.data.repository.TipoPenalidadRepositoryImpl
import com.example.registroestudiantes.domain.repository.AsignaturaRepository
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository
import com.example.registroestudiantes.data.remote.PlanetApiService
import com.example.registroestudiantes.data.repository.PlanetRepositoryImpl
import com.example.registroestudiantes.domain.repository.PlanetRepository
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetByIdUseCase
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "registro_estudiantes.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideEstudianteDao(db: AppDatabase): EstudianteDao =
        db.estudianteDao()

    @Provides
    fun provideAsignaturaDao(db: AppDatabase): AsignaturaDao =
        db.asignaturaDao()

    @Provides
    fun provideTipoPenalidadDao(db: AppDatabase): TipoPenalidadDao =
        db.tipoPenalidadDao()

    @Provides
    @Singleton
    fun provideEstudianteRepository(
        dao: EstudianteDao
    ): EstudianteRepository =
        EstudianteRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAsignaturaRepository(
        dao: AsignaturaDao
    ): AsignaturaRepository =
        AsignaturaRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideTipoPenalidadRepository(
        dao: TipoPenalidadDao
    ): TipoPenalidadRepository =
        TipoPenalidadRepositoryImpl(dao)


    private const val BASE_URL = "https://dragonball-api.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePlanetApiService(
        retrofit: Retrofit
    ): PlanetApiService =
        retrofit.create(PlanetApiService::class.java)

    @Provides
    @Singleton
    fun providePlanetRepository(
        api: PlanetApiService
    ): PlanetRepository =
        PlanetRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetPlanetsUseCase(
        repository: PlanetRepository
    ): GetPlanetsUseCase =
        GetPlanetsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPlanetByIdUseCase(
        repository: PlanetRepository
    ): GetPlanetByIdUseCase =
        GetPlanetByIdUseCase(repository)
}

