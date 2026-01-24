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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "estudiantes.db"
        ).build()

    @Provides
    fun provideDao(db: AppDatabase): EstudianteDao =
        db.estudianteDao()

    @Provides
    @Singleton
    fun provideRepository(
        dao: EstudianteDao
    ): EstudianteRepository =
        EstudianteRepositoryImpl(dao)
}
