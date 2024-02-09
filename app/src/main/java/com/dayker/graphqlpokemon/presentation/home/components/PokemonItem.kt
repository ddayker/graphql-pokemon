package com.dayker.graphqlpokemon.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dayker.graphqlpokemon.domain.model.Pokemon
import com.dayker.graphqlpokemon.presentation.common.AnimatedImage
import com.dayker.graphqlpokemon.presentation.common.PokemonType.Companion.setTypeGradientColorBrush

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            imageUrl = pokemon.img,
        )
        Box(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .background(
                    brush = setTypeGradientColorBrush(pokemon.type),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = pokemon.name.replaceFirstChar(Char::titlecase),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

