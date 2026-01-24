package com.example.registroestudiantes.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.entities.EstudianteEntity

@Database(
    entities = [EstudianteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estudianteDao(): EstudianteDao
}
