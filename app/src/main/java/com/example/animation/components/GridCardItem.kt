package com.example.animation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridCardItem(
    value: String,
    onClick: () -> Unit
) {
//    val configuration = LocalConfiguration.current
//    val width = configuration.screenWidthDp.dp
//    val height = configuration.screenHeightDp.dp

    Card(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(percent = 25),
        modifier = Modifier
            .width(100.dp)
            .height(200.dp)
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column() {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridCardItem("Built In Android") {
    }
}