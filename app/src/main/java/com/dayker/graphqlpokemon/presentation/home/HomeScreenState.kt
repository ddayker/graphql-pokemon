package com.dayker.graphqlpokemon.presentation.home

import com.dayker.graphqlpokemon.domain.model.Pokemon

data class HomeScreenState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val pokemons: List<Pokemon> = emptyList(),
    val endReached: Boolean = false,
    val page: Int = 0
)