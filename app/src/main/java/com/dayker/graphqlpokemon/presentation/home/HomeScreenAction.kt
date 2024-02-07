package com.dayker.graphqlpokemon.presentation.home

sealed class HomeScreenAction {
    data class ShowPokemonDetails(val id: Int) : HomeScreenAction()
}