package com.example.animation.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animation.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayButton(starting: MutableState<Boolean>) {

    IconButton(
        onClick = {
            starting.value = !starting.value
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
            if (starting.value) R.drawable.stop else R.drawable.play_arrow
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