package com.dayker.graphqlpokemon.presentation.details.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun CharacteristicBar(
    modifier: Modifier = Modifier,
    color: Color,
    characteristic: String,
    value: Int
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val percent: Float by animateFloatAsState(
            value.toFloat() / 100f,
            label = "",
            animationSpec = tween(
                durationMillis = 1500,
            )
        )
        Row {
            Text(
                text = characteristic,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "($value)",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        LinearProgressIndicator(
            strokeCap = StrokeCap.Round,
            progress = { percent },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(15.dp),
            color = color,
        )
    }
}