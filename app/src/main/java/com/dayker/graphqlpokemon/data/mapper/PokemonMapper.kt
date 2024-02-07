package com.dayker.graphqlpokemon.data.mapper

import com.dayker.graphqlpokemon.PokemonDetailsQuery
import com.dayker.graphqlpokemon.PokemonsQuery
import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.domain.model.PokemonDetails

fun Pokemon(
    dto: PokemonsQuery.Pokemon
): Pokemon = with(dto) {
    Pokemon(
        id = this.id,
        name = this.name,
        type = this.type.first().pokemon_v2_type?.name ?: "",
        img = this.img.first().sprites.toString().let {
            getFrontDefaultValue(it)
        } ?: ""
    )
}

fun PokemonDetails(
    dto: PokemonDetailsQuery.Pokemon
): PokemonDetails = with(dto) {
    PokemonDetails(
        id = this.id,
        name = this.name,
        type = this.type.first().pokemon_v2_type?.name ?: "",
        img = this.img.first().sprites.toString().let {
            getFrontDefaultValue(it)
        } ?: "",
        height = heightToCm(this.height),
        weight = weightToKg(this.weight),
        hp = mapStat(stats, "hp"),
        attack = mapStat(stats, "attack"),
        defence = mapStat(stats, "defense"),
    )
}

fun getFrontDefaultValue(input: String): String? {
    val matchResult = Regex("front_default=(https://[^,]+)").find(input)
    return matchResult?.groupValues?.get(1)
}

fun heightToCm(heightInDecimetres: Int?): Int = heightInDecimetres?.let { it * 10 } ?: 0

fun weightToKg(weightInHectogram: Int?): Int = weightInHectogram?.let { it / 10 } ?: 0

fun mapStat(stats: List<PokemonDetailsQuery.Stat>, statName: String): Int {
    val matchingStat = stats.find { it.pokemon_v2_stat?.name.equals(statName, ignoreCase = true) }
    return matchingStat?.base_stat ?: 0
}