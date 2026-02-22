package com.example.registroestudiantes.presentation.penalidad.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiantes.domain.model.TipoPenalidad
import com.example.registroestudiantes.domain.usecase.PenalidadUseCase.PenalidadUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPenalidadViewModel @Inject constructor(
    private val useCases: PenalidadUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ListPenalidadUIState())
    val state: StateFlow<ListPenalidadUIState> = _state.asStateFlow()

    init {
        cargar()
    }

    fun onEvent(event: ListPenalidadUIEvent) {
        when (event) {
            is ListPenalidadUIEvent.OnRefresh -> cargar()
            is ListPenalidadUIEvent.OnEliminar -> eliminar(event.penalidad)
        }
    }

    private fun cargar() {
        viewModelScope.launch {
            useCases.obtenerTodos()
                .onStart { _state.update { it.copy(isLoading = true, error = null) } }
                .catch { _ ->
                    _state.update { it.copy(isLoading = false, error = "Error cargando penalidades") }
                }
                .collect { lista ->
                    _state.update { it.copy(isLoading = false, penalidades = lista) }
                }
        }
    }

    private fun eliminar(penalidad: TipoPenalidad) {
        viewModelScope.launch {
            try {
                useCases.eliminar(penalidad.tipoId)
            } catch (e: Exception) {
                _state.update { it.copy(error = "Error eliminando penalidad") }
            }
        }
    }
}