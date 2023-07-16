package com.example.animation.components.animations

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CrossFadeAnimations() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Text("Cross Fade Animations", modifier = Modifier.padding(16.dp))
        }
        ColoredRectangle()
    }
}

@Composable
fun ColoredRectangle() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    var colorState by remember { mutableStateOf(primaryColor) }

    Crossfade(
        targetState = colorState,
        animationSpec = tween(durationMillis = 2000)
    ) {
        Surface(
            shape = RoundedCornerShape(30),
            color = it,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    colorState =
                        if (colorState == primaryColor) secondaryColor else primaryColor
                }
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrossFadeAnimationsPreview() {
    CrossFadeAnimations()
}