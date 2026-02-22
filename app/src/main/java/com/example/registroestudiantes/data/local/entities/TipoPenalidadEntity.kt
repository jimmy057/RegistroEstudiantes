package com.example.registroestudiantes.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TiposPenalidades",
    indices = [Index(value = ["Nombre"], unique = true)]
)
data class TipoPenalidadEntity(
    @PrimaryKey(autoGenerate = true)
    val TipoId: Int = 0,
    val Nombre: String,
    val Descripcion: String,
    val PuntosDescuento: Int
)