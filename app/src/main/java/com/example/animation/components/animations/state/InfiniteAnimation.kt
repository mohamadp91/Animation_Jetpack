package com.example.animation.components.animations.state

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.components.PlayButton

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InfiniteAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var starting = remember {
            mutableStateOf(false)
        }
        val infiniteTransition = rememberInfiniteTransition()
        val color = if (starting.value) {
            infiniteTransition.animateColor(
                initialValue = MaterialTheme.colorScheme.primary,
                targetValue = MaterialTheme.colorScheme.secondary,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            ).value
        } else MaterialTheme.colorScheme.secondary.copy(.7f)


        Box(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(200.dp)
                .background(
                    color,
                    shape = RoundedCornerShape(50.dp)
                ), contentAlignment = Alignment.Center
        ) {
            PlayButton(starting)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfiniteAnimationPreview() {
    InfiniteAnimation()
}