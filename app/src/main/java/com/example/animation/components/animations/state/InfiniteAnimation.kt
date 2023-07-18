package com.example.animation.components.animations.state

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InfiniteAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var starting by remember {
            mutableStateOf(false)
        }
        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = MaterialTheme.colorScheme.primary,
            targetValue = MaterialTheme.colorScheme.secondary,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
            )
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(200.dp)
                .background(
                    if (starting) color else MaterialTheme.colorScheme.secondary.copy(.7f),
                    shape = RoundedCornerShape(50.dp)
                ), contentAlignment = Alignment.Center
        ) {

            IconButton(
                onClick = {
                    starting = !starting
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(60.dp)
                    .animateContentSize()
                    .border(
                        BorderStroke(2.dp, Color.White), shape = CircleShape
                    )
            ) {
                val iconState = remember(starting) {
                    if (starting) R.drawable.stop else R.drawable.play_arrow
                }
                AnimatedContent(targetState = iconState,
                    transitionSpec = {
                            slideIntoContainer(AnimatedContentScope.SlideDirection.Up) + fadeIn() with slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Down
                            ) + fadeOut()
                    }) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "Button Icon",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfiniteAnimationPreview() {
    InfiniteAnimation()
}