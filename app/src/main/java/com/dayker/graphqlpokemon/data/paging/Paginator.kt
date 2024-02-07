package com.dayker.graphqlpokemon.data.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}