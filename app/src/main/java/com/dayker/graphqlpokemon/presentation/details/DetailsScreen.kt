package com.dayker.graphqlpokemon.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dayker.graphqlpokemon.R
import com.dayker.graphqlpokemon.core.util.Container
import com.dayker.graphqlpokemon.domain.model.PokemonDetails
import com.dayker.graphqlpokemon.presentation.common.AnimatedImage
import com.dayker.graphqlpokemon.presentation.common.ErrorStub
import com.dayker.graphqlpokemon.presentation.common.PokemonType
import com.dayker.graphqlpokemon.presentation.details.components.DetailsScreenTopBar
import com.dayker.graphqlpokemon.presentation.details.components.PokemonCharacteristics
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    windowSize: WindowSizeClass,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Container(flow = viewModel.actionFlow) { action ->
        when (action) {
            DetailsScreenAction.GoBack -> navController.navigateUp()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            DetailsScreenTopBar(
                name = state.pokemon?.name ?: "",
                backButtonAction = viewModel::onBackClicked
            )
        }
    ) {
        if (viewModel.state.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.scrim),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (viewModel.state.value.isNotFound) {
            ErrorStub(message = stringResource(R.string.pokemon_not_found))
        } else {
            val pokemon = state.pokemon
            if (pokemon != null) {
                PokemonDetailsContent(
                    modifier = Modifier.padding(it),
                    pokemon = pokemon,
                    windowSize = windowSize
                )
            } else {
                ErrorStub(message = stringResource(R.string.pokemon_not_found))
            }
        }
    }
}

@Composable
fun PokemonDetailsContent(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    pokemon: PokemonDetails
) {
    TwoPane(
        modifier = modifier,
        first = {
            AnimatedImage(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                imageUrl = pokemon.img,
                brush = PokemonType.setTypeGradientColorBrush(pokemon.type)
            )
        },
        second = {
            PokemonCharacteristics(pokemon = pokemon)
        },
        strategy =
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> VerticalTwoPaneStrategy(0.35f)
            else -> {
                HorizontalTwoPaneStrategy(0.35f)
            }
        },
        displayFeatures = listOf()
    )
}
