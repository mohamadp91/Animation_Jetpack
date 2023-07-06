package com.example.animation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animation.components.AppTopBar
import com.example.animation.components.GridCardItem
import com.example.animation.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        AppTopBar(navController = navController)
    }) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            columns = GridCells.Fixed(2),
            content = {
                items(AppScreens.values().filter { screen ->
                    screen != AppScreens.HomeScreen
                }) { item ->
                    GridCardItem(item.value) {
                        navController.navigate(item.name)
                    }
                }
            })
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewHome() {
    HomeScreen(navController = rememberNavController())
}