package com.example.registroestudiantes.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.usecase.EstudianteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListEstudianteViewModel @Inject constructor(
    private val useCases: EstudianteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ListEstudianteUIState())
    val state: StateFlow<ListEstudianteUIState> = _state

    init {
        observarEstudiantes()
    }

    private fun observarEstudiantes() {
        viewModelScope.launch {
            try {
                useCases.obtenerEstudiantes()
                    .onStart {
                        _state.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }
                    .collect { lista ->
                        _state.update {
                            it.copy(
                                estudiantes = lista,
                                isLoading = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar estudiantes"
                    )
                }
            }
        }
    }

    fun eliminar(estudiante: Estudiante) {
        viewModelScope.launch {
            try {
                useCases.eliminar(estudiante)
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "No se pudo eliminar el estudiante")
                }
            }
        }
    }
}



