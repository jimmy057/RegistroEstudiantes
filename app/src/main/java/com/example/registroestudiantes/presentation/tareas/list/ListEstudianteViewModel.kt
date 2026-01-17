package com.example.registroestudiantes.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.example.registroestudiantes.domain.model.Estudiante
import com.example.registroestudiantes.domain.repository.EstudianteRepository

@HiltViewModel
class ListEstudianteViewModel @Inject constructor(
    private val repository: EstudianteRepository
) : ViewModel() {

    var uiState by mutableStateOf(ListEstudianteUIState())
        private set

    init {
        cargar()
    }
    fun cargar() {
        viewModelScope.launch {
            uiState = uiState.copy(
                estudiantes = repository.obtenerTodos()
            )
        }
    }
    fun eliminar(estudiante: Estudiante) {
        viewModelScope.launch {
            repository.eliminar(estudiante)
            cargar()
        }
    }
}
