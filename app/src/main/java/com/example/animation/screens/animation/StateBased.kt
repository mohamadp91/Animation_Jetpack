package com.example.animation.screens.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animation.R
import com.example.animation.components.AppTopBar
import com.example.animation.components.animations.state.InfiniteAnimation

@Composable
fun StateBasedAnimations(navController: NavController) {
    val tabList = listOf(
        stringResource(R.string.state_based_tab1),
        stringResource(R.string.state_based_tab2),
        stringResource(R.string.state_based_tab3)
    )
    var selectedTab by remember {
        mutableStateOf(0)
    }

    Scaffold(topBar = {
        AppTopBar(
            navController = navController,
            title = "State based animations"
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = .7f)
            ) {
                tabList.forEachIndexed { index, title ->
                    Tab(selected = selectedTab == index,
                        text = {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        onClick = {
                            selectedTab = index
                        })
                }
            }

            when (selectedTab) {
                0 -> InfiniteAnimation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateBasedAnimationsPreview() {
    StateBasedAnimations(navController = rememberNavController())
}