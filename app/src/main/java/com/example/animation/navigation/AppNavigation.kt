package com.example.animation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animation.screens.ModifierScreen
import com.example.animation.screens.TypographyScreen
import com.example.animation.screens.animation.ContentChangeAnimations
import com.example.animation.screens.animation.LowLevelAnimations
import com.example.animation.screens.animation.StateBasedAnimations
import com.example.animation.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ) {

        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.ContentChange.name) {
            ContentChangeAnimations(navController)
        }
        composable(route = AppScreens.StateBased.name) {
            StateBasedAnimations(navController)
        }
        composable(route = AppScreens.LowLevelAnimations.name) {
            LowLevelAnimations(navController)
        }
        composable(route = AppScreens.TypographyFeatures.name) {
            TypographyScreen(navController)
        }
        composable(route = AppScreens.ModifierScreen.name) {
            ModifierScreen(navController)
        }
    }


}
