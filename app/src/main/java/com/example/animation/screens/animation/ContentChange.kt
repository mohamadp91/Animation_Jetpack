package com.example.animation.screens.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.animation.R
import com.example.animation.components.animations.AppearanceAnimation
import com.example.animation.components.animations.SwapContentAnimation

@Composable
fun ContentChangeAnimations() {
    val context = LocalContext.current
    val tabTitles = listOf(
        context.getString(R.string.content_change_tab1),
        context.getString(R.string.content_change_tab2),
        context.getString(R.string.content_change_tab3),
    )
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.primary
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
            0 -> AppearanceAnimation()
            1 -> SwapContentAnimation()
            2 -> {
                //todo : implement
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentChangeAnimationsPreview() {
    ContentChangeAnimations()
}
