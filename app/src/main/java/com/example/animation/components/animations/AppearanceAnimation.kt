package com.example.animation.components.animations

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import com.example.animation.R
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
        Triple("fade", fadeIn(), fadeOut()),
        Triple("slide horizontally", slideInHorizontally(), slideOutHorizontally()),
        Triple("slide vertically", slideInVertically(), slideOutVertically()),
        Triple("expand, shrink", expandIn(), shrinkOut()),
        Triple("expand,shrink horizontally", expandHorizontally(), shrinkHorizontally()),
        Triple("expand,shrink vertically", expandVertically(), shrinkVertically()),
        Triple("scale", scaleIn(), scaleOut()),
        Triple(
            "slide initially", slideIn(initialOffset = { size -> size.center }),
            slideOut(targetOffset = { size -> size.center }),
        )
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
                    value = item.first,
                    cardSize = Pair(cardWidth, cardHeight),
                    CardImage = { AnimatedImage(item, Pair(cardWidth, cardHeight)) }
                ) {}
            }
        })
}

@Composable
fun AnimatedImage(
    item: Triple<String, EnterTransition, ExitTransition>,
    cardSize: Pair<Dp, Dp>
) {
    val visibility = remember { mutableStateOf(true) }

    val columnHeight = cardSize.first / 3 * 2
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(columnHeight)
            .clickable { visibility.value = !visibility.value },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = visibility.value,
            enter = item.second,
            exit = item.third,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Animated Image",
                modifier = Modifier
                    .size(
                        cardSize.first / 2,
                        columnHeight / 2
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }

}
