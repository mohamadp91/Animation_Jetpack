package com.example.animation.components.animations

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TextContentAnimations() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var textFieldState by remember { mutableStateOf("") }
        var textState by remember { mutableStateOf("") }
        Text(
            text = "Please enter a character",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            OutlinedTextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = if (textFieldState.length <= 1) {
                        it
                    } else
                        ""
                },
                label = { Text(text = "Char") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    textState += textFieldState
                    textFieldState = ""
                }),
            )
        }
        AnimatedContent(targetState = textState,
            transitionSpec = {
                slideInVertically() with scaleOut()
            }) {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    ),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())
    }
}


@Preview(showBackground = true)
@Composable
fun ContentAnimationsPreview() {
    TextContentAnimations()
}