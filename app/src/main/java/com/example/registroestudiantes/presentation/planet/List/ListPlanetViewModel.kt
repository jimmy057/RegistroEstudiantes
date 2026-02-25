package com.example.registroestudiantes.presentation.planet.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetsUseCase

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(ListPlanetUIState())
    val uiState: State<ListPlanetUIState> = _uiState

    init {
        onEvent(ListPlanetUIEvent.LoadPlanets)
    }

    fun onEvent(event: ListPlanetUIEvent) {
        when (event) {

            ListPlanetUIEvent.LoadPlanets -> {
                loadPlanets()
            }

            is ListPlanetUIEvent.OnPlanetClick -> {
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val planets = getPlanetsUseCase()
                _uiState.value = _uiState.value.copy(
                    planets = planets,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}