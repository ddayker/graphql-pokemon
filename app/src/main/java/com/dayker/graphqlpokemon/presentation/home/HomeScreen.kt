package com.dayker.graphqlpokemon.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.DETAILS_SCREEN_ROUTE
import com.dayker.graphqlpokemon.core.util.Container
import com.dayker.graphqlpokemon.presentation.common.ErrorStub
import com.dayker.graphqlpokemon.presentation.home.components.PokemonGrid

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Container(flow = viewModel.actionFlow) { action ->
        when (action) {
            is HomeScreenAction.ShowPokemonDetails -> {
                navController.navigate(route = "${DETAILS_SCREEN_ROUTE}/${action.id}")
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (state.error.isNullOrBlank()) {
            PokemonGrid(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                items = state.pokemons,
                imageOnClicked = {
                    viewModel.onItemClicked(it)
                },
                isLoading = state.isLoading,
                isEndPointReached = state.endReached,
                loadNextPage = viewModel::loadNextPage
            )
        } else {
            state.error?.let {
                ErrorStub(message = it)
            }
        }
    }
}