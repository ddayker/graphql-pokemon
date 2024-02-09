package com.dayker.graphqlpokemon.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.POKEMON_ID_PARAM
import com.dayker.graphqlpokemon.domain.usecase.GetPokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonDetails: GetPokemonDetails,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    private val _actionFlow = MutableSharedFlow<DetailsScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    init {
        val id = savedStateHandle.get<Int>(POKEMON_ID_PARAM)
        viewModelScope.launch {
            if (id != null) {
                getPokemonDetails(id)
                    .onSuccess { data ->
                        _state.update {
                            DetailsScreenState(
                                isLoading = false,
                                isNotFound = false,
                                pokemon = data
                            )
                        }
                    }
                    .onFailure {
                        DetailsScreenState(
                            isLoading = false,
                            isNotFound = true,
                        )
                    }
            } else {
                DetailsScreenState(
                    isLoading = false,
                    isNotFound = true,
                )
            }
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _actionFlow.emit(DetailsScreenAction.GoBack)
        }
    }
}