package com.example.registroestudiantes.di

import android.content.Context
import androidx.room.Room
import com.example.registroestudiantes.data.local.dao.AsignaturaDao
import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.dao.TipoPenalidadDao
import com.example.registroestudiantes.data.local.databases.AppDatabase
import com.example.registroestudiantes.data.remote.PlanetApi
import com.example.registroestudiantes.data.repository.AsignaturaRepositoryImpl
import com.example.registroestudiantes.data.repository.EstudianteRepositoryImpl
import com.example.registroestudiantes.data.repository.TipoPenalidadRepositoryImpl
import com.example.registroestudiantes.domain.repository.AsignaturaRepository
import com.example.registroestudiantes.domain.repository.EstudianteRepository
import com.example.registroestudiantes.domain.repository.TipoPenalidadRepository
import com.example.registroestudiantes.data.repository.PlanetRepositoryImpl
import com.example.registroestudiantes.domain.repository.PlanetRepository
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetByIdUseCase
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetsUseCase
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.moshi.MoshiConverterFactory
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


    private const val BASE_URL = "https://dragonball-api.com/api/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun providePlanetApi(
        retrofit: Retrofit
    ): PlanetApi =
        retrofit.create(PlanetApi::class.java)

    @Provides
    @Singleton
    fun providePlanetRepository(
        api: PlanetApi
    ): PlanetRepository =
        PlanetRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetPlanetsUseCase(
        repository: PlanetRepository
    ) = GetPlanetsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPlanetByIdUseCase(
        repository: PlanetRepository
    ) = GetPlanetByIdUseCase(repository)
}

