package com.example.animation.components.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContentSizeAnimation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldBlock(label = "Not animated")
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldBlock(label = "Animated", modifier = Modifier.animateContentSize())
    }
}

@Composable
fun TextFieldBlock(label: String, modifier: Modifier = Modifier) {
    var textFieldValue by remember { mutableStateOf("") }
    val shape = RoundedCornerShape(percent = 25)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = shape
            )
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = shape
            )
            .padding(12.dp)
            .width(170.dp)
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            label = { Text(label) },
            modifier = modifier
                .padding(24.dp),
            maxLines = 10
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentSizeAnimationPreview() {
    ContentSizeAnimation()
}