package com.example.registroestudiantes.presentation.planet.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import com.example.registroestudiantes.data.Resource.Resource
import com.example.registroestudiantes.domain.usecase.PlanetUseCase.GetPlanetByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetByIdUseCase: GetPlanetByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailPlanetUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("id")?.toInt() ?: 1
        onEvent(DetailPlanetUIEvent.LoadPlanet(id))
    }

    fun onEvent(event: DetailPlanetUIEvent) {
        when (event) {
            is DetailPlanetUIEvent.LoadPlanet -> loadPlanet(event.id)
        }
    }

    private fun loadPlanet(id: Int) {
        viewModelScope.launch {

            _uiState.value = DetailPlanetUiState(isLoading = true)

            when (val result = getPlanetByIdUseCase(id)) {

                is Resource.Success -> {
                    _uiState.value = DetailPlanetUiState(
                        planet = result.data
                    )
                }

                is Resource.Error -> {
                    _uiState.value = DetailPlanetUiState(
                        error = result.message ?: "Unknown error"
                    )
                }

                is Resource.Loading -> {
                    _uiState.value = DetailPlanetUiState(isLoading = true)
                }
            }
        }
    }
}