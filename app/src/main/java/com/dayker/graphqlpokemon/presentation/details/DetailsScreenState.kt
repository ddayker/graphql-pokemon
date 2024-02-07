package com.dayker.graphqlpokemon.presentation.details

import com.dayker.graphqlpokemon.domain.model.PokemonDetails

data class DetailsScreenState(
    val isLoading: Boolean = true,
    val isNotFound: Boolean = false,
    val pokemon: PokemonDetails? = null
)
