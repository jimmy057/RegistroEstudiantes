package com.example.registroestudiantes.data.local.mapper

import com.example.registroestudiantes.data.local.entities.TipoPenalidadEntity
import com.example.registroestudiantes.domain.model.TipoPenalidad

fun TipoPenalidadEntity.toDomain() = TipoPenalidad(
    tipoId = TipoId,
    nombre = Nombre,
    descripcion = Descripcion,
    puntosDescuento = PuntosDescuento
)

fun TipoPenalidad.toEntity() = TipoPenalidadEntity(
    TipoId = tipoId,
    Nombre = nombre,
    Descripcion = descripcion,
    PuntosDescuento = puntosDescuento
)