package com.dayker.graphqlpokemon.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dayker.graphqlpokemon.core.util.Container

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Container(flow = viewModel.actionFlow) { action ->
        when (action) {
            DetailsScreenAction.GoBack -> navController.navigateUp()
        }
    }
}