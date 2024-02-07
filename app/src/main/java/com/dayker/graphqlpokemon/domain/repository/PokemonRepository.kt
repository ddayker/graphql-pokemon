package com.dayker.graphqlpokemon.domain.repository

import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.model.PokemonDetails

interface PokemonRepository {

    suspend fun getPokemons(page: Int, limit: Int): Result<List<Pokemon>>

    suspend fun getPokemonDetails(id: Int): Result<PokemonDetails>
}