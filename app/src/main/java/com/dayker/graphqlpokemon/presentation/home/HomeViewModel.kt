package com.dayker.graphqlpokemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.graphqlpokemon.data.paging.DefaultPaginator
import com.dayker.graphqlpokemon.domain.usecase.GetPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemons: GetPokemons
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _actionFlow = MutableSharedFlow<HomeScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private val paginator = DefaultPaginator(
        initialKey = state.value.page,
        onLoadUpdated = { value ->
            _state.update {
                state.value.copy(
                    isLoading = value
                )
            }
        },
        onRequest = { nextPage ->
            getPokemons(nextPage, PAGING_LIMIT)
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = { error ->
            _state.update {
                state.value.copy(
                    error = error?.localizedMessage
                )
            }
        },
        onSuccess = { items, newKey ->
            _state.update {
                state.value.copy(
                    pokemons = state.value.pokemons + items,
                    page = newKey,
                    endReached = items.isEmpty()
                )
            }
        }
    )

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun onItemClicked(id: Int) {
        viewModelScope.launch {
            _actionFlow.emit(HomeScreenAction.ShowPokemonDetails(id = id))
        }
    }

    companion object {
        const val PAGING_LIMIT = 20
    }
}