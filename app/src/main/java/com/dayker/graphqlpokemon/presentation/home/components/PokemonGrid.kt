package com.dayker.graphqlpokemon.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dayker.graphqlpokemon.domain.model.Pokemon

@Composable
fun PokemonGrid(
    modifier: Modifier = Modifier,
    items: List<Pokemon>,
    imageOnClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    isEndPointReached: Boolean = false,
    loadNextPage: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            )
        }
        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 15.dp,
            horizontalArrangement = Arrangement.spacedBy(17.dp),
            content = {
                repeat(2) {
                    item {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
                items(items.size) { i ->
                    val pokemon = items[i]
                    if (i >= items.size - 1 && !isEndPointReached && !isLoading) {
                        loadNextPage()
                    }
                    PokemonItem(pokemon = pokemon, modifier = Modifier.clickable {
                        imageOnClicked(pokemon.id)
                    })
                }
            })
    }
}