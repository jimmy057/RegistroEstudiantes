package com.example.registroestudiantes.presentation.asignatura.Edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiantes.domain.model.Asignatura
import com.example.registroestudiantes.domain.usecase.AsignaturaUseCases.AsignaturaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAsignaturaViewModel @Inject constructor(
    private val useCases: AsignaturaUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(EditAsignaturaUIState())
    val state: StateFlow<EditAsignaturaUIState> = _state

    fun cargarAsignatura(id: Int?) {
        if (id == null || id <= 0) return

        viewModelScope.launch {
            val asignatura = useCases.obtenerPorId(id)
            asignatura?.let {
                _state.update { current ->
                    current.copy(
                        asignaturaId = it.asignaturaId,
                        codigo = it.codigo,
                        nombre = it.nombre,
                        aula = it.aula,
                        creditos = it.creditos.toString(),
                        mensaje = null,
                        error = null
                    )
                }
            }
        }
    }

    fun onEvent(event: EditAsignaturaUIEvent) {
        when (event) {
            is EditAsignaturaUIEvent.OnCodigoChange ->
                _state.update { current ->
                    current.copy(codigo = event.value, error = null, mensaje = null)
                }

            is EditAsignaturaUIEvent.OnNombreChange ->
                _state.update { current ->
                    current.copy(nombre = event.value, error = null, mensaje = null)
                }

            is EditAsignaturaUIEvent.OnAulaChange ->
                _state.update { current ->
                    current.copy(aula = event.value, error = null, mensaje = null)
                }

            is EditAsignaturaUIEvent.OnCreditosChange ->
                _state.update { current ->
                    current.copy(creditos = event.value, error = null, mensaje = null)
                }

            EditAsignaturaUIEvent.OnGuardar -> guardar()
        }
    }

    private fun guardar() {
        viewModelScope.launch {
            val s = state.value

            if (s.codigo.isBlank() || s.nombre.isBlank() || s.aula.isBlank() || s.creditos.isBlank()) {
                _state.update { it.copy(error = "Todos los campos son obligatorios") }
                return@launch
            }

            val creditosInt = s.creditos.toIntOrNull()
            if (creditosInt == null || creditosInt <= 0) {
                _state.update { it.copy(error = "Créditos deben ser un número positivo") }
                return@launch
            }

            val asignatura = Asignatura(
                asignaturaId = s.asignaturaId,
                codigo = s.codigo.trim(),
                nombre = s.nombre.trim(),
                aula = s.aula.trim(),
                creditos = creditosInt
            )

            useCases.guardar(asignatura)

            _state.update {
                it.copy(
                    mensaje = "Asignatura guardada correctamente",
                    error = null
                )
            }
        }
    }
}