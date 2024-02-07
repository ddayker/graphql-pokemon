package com.dayker.graphqlpokemon.data.datasource.remote


import com.apollographql.apollo3.ApolloClient
import com.dayker.graphqlpokemon.PokemonDetailsQuery
import com.dayker.graphqlpokemon.PokemonsQuery
import com.dayker.graphqlpokemon.data.datasource.PokemonDataSource
import com.dayker.graphqlpokemon.data.mapper.Pokemon
import com.dayker.graphqlpokemon.data.mapper.PokemonDetails
import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.model.PokemonDetails
import javax.inject.Inject

class GraphQLPokemonDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) : PokemonDataSource {

    override suspend fun getPokemons(page: Int, limit: Int): List<Pokemon> {
        val offset = page * limit
        return apolloClient
            .query(PokemonsQuery(offset = offset, limit = limit))
            .execute()
            .data
            ?.pokemon
            ?.map(::Pokemon)
            ?: emptyList()
    }

    override suspend fun getPokemonDetails(id: Int): PokemonDetails? {
        return apolloClient
            .query(PokemonDetailsQuery(id = id))
            .execute()
            .data
            ?.pokemon
            ?.map(::PokemonDetails)
            ?.first()
    }
}