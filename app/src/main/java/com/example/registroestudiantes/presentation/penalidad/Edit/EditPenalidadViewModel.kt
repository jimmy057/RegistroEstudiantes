package com.example.registroestudiantes.presentation.penalidad.Edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.PenalidadUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPenalidadViewModel @Inject constructor(
    private val useCases: PenalidadUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(EditPenalidadUIState())
    val state: StateFlow<EditPenalidadUIState> = _state

    fun setPenalidadId(id: Int) {
        if (id <= 0) return

        viewModelScope.launch {
            val penalidad = useCases.obtenerPorId(id)
            penalidad?.let {
                _state.update {
                    it.copy(
                        penalidadId = penalidad.tipoId,
                        nombre = penalidad.nombre,
                        descripcion = penalidad.descripcion,
                        puntos = penalidad.puntosDescuento.toString(),
                        mensaje = null,
                        error = null
                    )
                }
            }
        }
    }

    fun onEvent(event: EditPenalidadUIEvent) {
        when (event) {
            is EditPenalidadUIEvent.OnNombreChange ->
                _state.update { it.copy(nombre = event.value, error = null, mensaje = null) }

            is EditPenalidadUIEvent.OnDescripcionChange ->
                _state.update { it.copy(descripcion = event.value, error = null, mensaje = null) }

            is EditPenalidadUIEvent.OnPuntosChange ->
                _state.update { it.copy(puntos = event.value, error = null, mensaje = null) }

            EditPenalidadUIEvent.OnGuardar -> guardar()
        }
    }

    private fun guardar() {
        viewModelScope.launch {
            val s = state.value

            if (s.nombre.isBlank() || s.descripcion.isBlank() || s.puntos.isBlank()) {
                _state.update { it.copy(error = "Todos los campos son obligatorios") }
                return@launch
            }

            val puntosInt = s.puntos.toIntOrNull()
            if (puntosInt == null || puntosInt < 0) {
                _state.update { it.copy(error = "Puntos inválidos") }
                return@launch
            }

            val penalidad = TipoPenalidad(
                tipoId = s.penalidadId,
                nombre = s.nombre.trim(),
                descripcion = s.descripcion.trim(),
                puntosDescuento = puntosInt
            )

            useCases.guardar(penalidad)

            _state.update {
                it.copy(
                    mensaje = "Penalidad guardada correctamente",
                    error = null
                )
            }
        }
    }
}