package com.dayker.graphqlpokemon.data.datasource

import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.model.PokemonDetails


interface PokemonDataSource {
    suspend fun getPokemons(page: Int, limit: Int): List<Pokemon>
    suspend fun getPokemonDetails(id: Int): PokemonDetails?
}