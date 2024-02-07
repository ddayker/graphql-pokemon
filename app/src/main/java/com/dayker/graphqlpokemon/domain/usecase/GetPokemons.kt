package com.dayker.graphqlpokemon.domain.usecase

import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(page: Int, limit: Int): Result<List<Pokemon>> =
        repository.getPokemons(page = page, limit = limit)
}