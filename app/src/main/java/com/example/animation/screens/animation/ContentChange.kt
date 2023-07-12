package com.example.animation.screens.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animation.R
import com.example.animation.components.AppTopBar
import com.example.animation.components.animations.SwapContentAnimation
import com.example.animation.components.animations.VisibilityAnimations

@Composable
fun ContentChangeAnimations(navController: NavController) {
    val context = LocalContext.current
    val tabTitles = listOf(
        context.getString(R.string.content_change_tab1),
        context.getString(R.string.content_change_tab2),
        context.getString(R.string.content_change_tab3),
    )
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        AppTopBar(
            navController = navController,
            title = "Content Change Animations"
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = .7f)
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> VisibilityAnimations()
                1 -> SwapContentAnimation()
                3 -> {
                    //todo : implement
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentChangeAnimationsPreview() {
    ContentChangeAnimations(rememberNavController())
}
