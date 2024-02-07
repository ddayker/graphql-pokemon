package com.dayker.graphqlpokemon.data.di

import com.apollographql.apollo3.ApolloClient
import com.dayker.graphqlpokemon.data.datasource.PokemonDataSource
import com.dayker.graphqlpokemon.data.datasource.remote.GraphQLPokemonDataSource
import com.dayker.graphqlpokemon.data.repository.PokemonRepositoryImpl
import com.dayker.graphqlpokemon.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    companion object {

        const val POKEAPI_GRAPHQL_ENDPOINT = "https://beta.pokeapi.co/graphql/v1beta"

        @Provides
        @Singleton
        fun provideApolloClient(): ApolloClient {
            return ApolloClient.Builder()
                .serverUrl(POKEAPI_GRAPHQL_ENDPOINT)
                .build()
        }
    }

    @Binds
    abstract fun bindPokemonDatasource(
        impl: GraphQLPokemonDataSource
    ): PokemonDataSource

    @Binds
    abstract fun bindPokemonRepo(
        impl: PokemonRepositoryImpl
    ): PokemonRepository
}