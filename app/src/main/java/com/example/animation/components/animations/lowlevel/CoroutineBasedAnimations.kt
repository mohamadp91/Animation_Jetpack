package com.example.animation.components.animations.lowlevel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun CoroutineBasedAnimations() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DoubleClickToLike()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoubleClickToLike() {
    val shape = RoundedCornerShape(50.dp)

    var isDoubleClicked by remember {
        mutableStateOf(false)
    }
    var likeScale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = isDoubleClicked) {
        likeScale.animateTo(
            targetValue = if (isDoubleClicked) 1.2f else 0f,
            animationSpec = tween(easing = LinearOutSlowInEasing)
        )
        likeScale.animateTo(
            if (isDoubleClicked) 1f else 0f,
            animationSpec = tween(easing = LinearOutSlowInEasing)
        )
        delay(300)
        likeScale.animateTo(targetValue = 0f)
        isDoubleClicked = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.2f)
            .padding(16.dp)
            .background(color = Color.Transparent, shape = shape)
            .border(
                BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                shape = shape
            )
            .combinedClickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onDoubleClick = {
                    if (!isDoubleClicked)
                        isDoubleClicked = true
                },
                onClick = {}),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = !isDoubleClicked,
            enter = fadeIn(animationSpec = tween(400, delayMillis = 100)),
            exit = fadeOut(animationSpec = tween(100))
        ) {
            Text(text = "Double click to like")
        }
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Like",
            tint = Color.Red,
            modifier = Modifier
                .size(70.dp)
                .scale(likeScale.value)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCoroutineBasedAnimations() {
    CoroutineBasedAnimations()
}