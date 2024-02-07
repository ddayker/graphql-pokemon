package com.dayker.graphqlpokemon.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val type: String,
    val img: String,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defence: Int
)