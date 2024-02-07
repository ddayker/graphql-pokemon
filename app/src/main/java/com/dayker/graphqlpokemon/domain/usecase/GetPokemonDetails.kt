package com.dayker.graphqlpokemon.domain.usecase

import com.dayker.graphqlpokemon.domain.model.PokemonDetails
import com.dayker.graphqlpokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetails @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<PokemonDetails> =
        repository.getPokemonDetails(id)
}