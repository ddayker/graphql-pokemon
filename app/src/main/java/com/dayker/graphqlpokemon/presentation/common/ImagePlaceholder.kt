package com.dayker.graphqlpokemon.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.dayker.graphqlpokemon.R

@Composable
fun ImagePlaceholder(
    modifier: Modifier = Modifier,
    scaleValue: Float
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.55f)
                .graphicsLayer {
                    scaleX = scaleValue
                    scaleY = scaleValue
                }
        )
    }
}