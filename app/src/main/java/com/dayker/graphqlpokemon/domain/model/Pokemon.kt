package com.dayker.graphqlpokemon.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val img: String
)