package com.example.animation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animation.components.AppTopBar
import com.example.animation.components.GridCardItem
import com.example.animation.navigation.AppScreens
import com.example.animation.util.getScreenSize

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        AppTopBar(
            isMainScreen = true,
            title = "Home",
            navController = navController
        )
    }) {
        CustomGridLayout(
            paddingValues = it,
            navController = navController
        )
    }
}

@Composable
fun CustomGridLayout(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val (screenWidth, screenHeight) = getScreenSize(LocalConfiguration.current)

    val cardWidth = (screenWidth / 2 - 24).dp
    val cardHeight = (screenHeight / 3 - 24).dp

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        columns = GridCells.Fixed(2),
        content = {
            items(AppScreens.values().filter { screen ->
                screen != AppScreens.HomeScreen
            }) { item ->
                GridCardItem(
                    item.value,
                    Pair(cardWidth, cardHeight),
                ) {
                    navController.navigate(item.name)
                }
            }
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewHome() {
    HomeScreen(navController = rememberNavController())
}