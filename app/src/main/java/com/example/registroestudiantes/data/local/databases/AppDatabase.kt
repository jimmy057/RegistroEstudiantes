package com.example.registroestudiantes.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registroestudiantes.data.local.dao.AsignaturaDao
import com.example.registroestudiantes.data.local.dao.EstudianteDao
import com.example.registroestudiantes.data.local.dao.TipoPenalidadDao
import com.example.registroestudiantes.data.local.entities.AsignaturaEntity
import com.example.registroestudiantes.data.local.entities.EstudianteEntity
import com.example.registroestudiantes.data.local.entities.TipoPenalidadEntity

@Database(
    entities = [
        EstudianteEntity::class,
        AsignaturaEntity::class,
        TipoPenalidadEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun estudianteDao(): EstudianteDao

    abstract fun asignaturaDao(): AsignaturaDao

    abstract fun tipoPenalidadDao(): TipoPenalidadDao
}
