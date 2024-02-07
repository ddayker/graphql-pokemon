package com.dayker.graphqlpokemon.data.repository

import com.dayker.graphqlpokemon.data.datasource.PokemonDataSource
import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.model.PokemonDetails
import com.dayker.graphqlpokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val datasource: PokemonDataSource
) : PokemonRepository {
    override suspend fun getPokemons(page: Int, limit: Int): Result<List<Pokemon>> = runCatching {
        datasource.getPokemons(page, limit)
    }

    override suspend fun getPokemonDetails(id: Int): Result<PokemonDetails> = runCatching {
        datasource.getPokemonDetails(id)!!
    }
}