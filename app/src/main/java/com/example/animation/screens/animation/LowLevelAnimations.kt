package com.example.animation.screens.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animation.components.AppTopBar
import com.example.animation.components.animations.lowlevel.CoroutineBasedAnimations


@Composable
fun LowLevelAnimations(navController: NavController) {
    Scaffold(topBar = { AppTopBar(navController = navController) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(
                text = "Coroutine based animations",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            CoroutineBasedAnimations()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCoroutineBasedAnimation() {
    LowLevelAnimations(navController = rememberNavController())
}