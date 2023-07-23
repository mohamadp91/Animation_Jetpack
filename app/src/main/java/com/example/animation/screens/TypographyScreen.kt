package com.example.animation.screens

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
import com.example.animation.components.typography.TextFieldBasics
import com.example.animation.components.typography.TypographyBasics

@Composable
fun TypographyScreen(navController: NavController) {
    val tabList = listOf(
        stringResource(R.string.typo_tab1),
        stringResource(R.string.typo_tab2)
    )
    Scaffold(topBar = {
        AppTopBar(
            navController = navController,
            title = "Typography"
        )
    }) {
        var selectedTab by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = .7f)
            ) {
                tabList.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            }
            when((selectedTab)) {
                0 -> TypographyBasics()
                1 -> TextFieldBasics()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TypographyScreenPreview() {
    TypographyScreen(navController = rememberNavController())
}
