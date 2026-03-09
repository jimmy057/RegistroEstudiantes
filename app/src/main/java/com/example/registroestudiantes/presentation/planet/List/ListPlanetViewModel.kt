package com.example.registroestudiantes.presentation.planet.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.registroestudiantes.data.Resource.Resource
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListPlanetUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(ListPlanetUIEvent.LoadPlanets)
    }

    fun onEvent(event: ListPlanetUIEvent) {
        when (event) {

            is ListPlanetUIEvent.LoadPlanets -> loadPlanets()

            is ListPlanetUIEvent.OnPlanetClick -> {
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {

            _uiState.value = ListPlanetUiState(isLoading = true)

            when (val result = getPlanetsUseCase()) {

                is Resource.Success -> {
                    _uiState.value = ListPlanetUiState(
                        planets = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _uiState.value = ListPlanetUiState(
                        error = result.message ?: "Unknown error"
                    )
                }

                is Resource.Loading -> {
                    _uiState.value = ListPlanetUiState(isLoading = true)
                }
            }
        }
    }
}