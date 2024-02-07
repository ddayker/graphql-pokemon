package com.dayker.graphqlpokemon.presentation.details

sealed class DetailsScreenAction {
    data object GoBack : DetailsScreenAction()
}