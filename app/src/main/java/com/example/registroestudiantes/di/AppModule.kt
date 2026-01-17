package com.example.registroestudiantes.di

import android.content.Context
import androidx.room.Room
import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.databases.AppDatabase
import com.example.registroestudiantes.data.repository.EstudianteRepositoryImpl
import com.example.registroestudiantes.domain.repository.EstudianteRepository
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
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "estudiantes_db"
        ).build()
    }

    @Provides
    fun provideEstudianteDao(
        db: AppDatabase
    ): EstudianteDao = db.estudianteDao()

    @Provides
    @Singleton
    fun provideEstudianteRepository(
        dao: EstudianteDao
    ): EstudianteRepository {
        return EstudianteRepositoryImpl(dao)
    }
}