package com.example.registroestudiantes.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.usecase.EstudianteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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
        viewModelScope.launch {
            useCases.obtenerTodos().collect { lista ->
                _state.update {
                    it.copy(
                        estudiantes = lista,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: ListEstudianteUIEvent) {
        when (event) {
            is ListEstudianteUIEvent.OnEliminar -> {
                eliminar(event.estudiante)
            }
        }
    }

    private fun eliminar(estudiante: Estudiante) {
        viewModelScope.launch {
            useCases.eliminar(estudiante)
        }
    }
}



