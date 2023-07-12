package com.example.animation.components.animations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SwapContentAnimation() {
    Column(modifier = Modifier.fillMaxSize()) {
        TextContentAnimations()
        CrossFadeAnimations()
    }
}