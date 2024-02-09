package com.dayker.graphqlpokemon.presentation.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlin.random.Random


@Composable
fun AnimatedImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    brush: Brush? = null,
    color: Color = MaterialTheme.colorScheme.background
) {
    val scaleValue by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing,
                delayMillis = Random.nextInt(200, 1500)

            ),
            repeatMode = RepeatMode.Reverse,
        ), label = ""
    )
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build(),
        contentDescription = null,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Error || state is AsyncImagePainter.State.Loading) {
            ImagePlaceholder(
                modifier = Modifier.fillMaxHeight(0.75f),
                scaleValue = scaleValue
            )
        } else {
            SubcomposeAsyncImageContent(
                contentScale = ContentScale.Fit,
                modifier = if (brush != null)
                    Modifier
                        .graphicsLayer {
                            scaleX = scaleValue
                            scaleY = scaleValue
                        }
                        .background(
                            shape = RoundedCornerShape(20.dp),
                            brush = brush,
                        ) else
                    Modifier
                        .graphicsLayer {
                            scaleX = scaleValue
                            scaleY = scaleValue
                        }
                        .background(
                            shape = RoundedCornerShape(20.dp),
                            color = color
                        )
            )
        }
    }
}