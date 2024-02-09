package com.dayker.graphqlpokemon.presentation.details.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.graphqlpokemon.R
import com.dayker.graphqlpokemon.domain.model.PokemonDetails
import com.dayker.graphqlpokemon.presentation.common.PokemonType

@Composable
fun PokemonCharacteristics(
    modifier: Modifier = Modifier,
    pokemon: PokemonDetails
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CharacteristicColumn(
                characteristic = stringResource(R.string.type),
                value = pokemon.type
            )
            VerticalDivider()
            CharacteristicColumn(
                characteristic = stringResource(R.string.height),
                value = pokemon.height.toString() + stringResource(R.string.cm)
            )
            VerticalDivider()
            CharacteristicColumn(
                characteristic = stringResource(R.string.weight),
                value = pokemon.weight.toString() + stringResource(R.string.kg)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))
        CharacteristicBar(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 30.dp),
            color = PokemonType.setTypeColor(pokemon.type),
            characteristic = stringResource(R.string.health),
            value = pokemon.hp
        )
        CharacteristicBar(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 30.dp),
            color = PokemonType.setTypeColor(pokemon.type),
            characteristic = stringResource(R.string.attack),
            value = pokemon.attack
        )
        CharacteristicBar(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 30.dp),
            color = PokemonType.setTypeColor(pokemon.type),
            characteristic = stringResource(R.string.defence),
            value = pokemon.defence
        )
    }
}

