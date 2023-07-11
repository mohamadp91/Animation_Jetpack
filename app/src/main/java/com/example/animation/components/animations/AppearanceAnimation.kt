package com.example.animation.components.animations

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import com.example.animation.components.GridCardItem
import com.example.animation.util.getScreenSize

@Composable
fun AppearanceAnimation() {
    AppearanceGridLayout()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppearanceGridLayout() {
    val appearanceAnimations = listOf(
        "fade in" to fadeIn(),
        "fade out" to fadeOut(),
        "slide in horizontally" to slideInHorizontally(),
        "slide out horizontally" to slideOutHorizontally(),
        "slide in vertically" to slideInVertically(),
        "slide out vertically" to slideOutVertically(),
        "expand in" to expandIn(),
        "shrink out" to shrinkOut(),
        "expand horizontally" to expandHorizontally(),
        "shrink horizontally" to shrinkHorizontally(),
        "expand vertically" to shrinkVertically(),
        "shrink vertically" to shrinkVertically(),
        "shrink out" to shrinkOut(),
        "scale in" to scaleIn(),
        "scale out" to scaleOut(),
        "slide in" to slideIn(initialOffset = { size -> size.center }),
        "slide out" to slideOut(targetOffset = { size -> size.center }),
    )

    val density = LocalDensity.current
    val (screenWidth, screenHeight) = getScreenSize(LocalConfiguration.current)
    val cardWidth = (screenWidth / 2 - 24).dp
    val cardHeight = (screenHeight / 4 - 24).dp

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(appearanceAnimations) { item ->
                GridCardItem(
                    item.first,
                    Pair(cardWidth, cardHeight),
                ) {}
            }
        })

}

@Preview(showBackground = true)
@Composable
fun Prev() {
    AppearanceAnimation()
}
