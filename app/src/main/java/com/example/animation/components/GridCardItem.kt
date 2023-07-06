package com.example.animation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridCardItem(
    value: String,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val cardWidth = (configuration.screenWidthDp / 2 - 24).dp
    val cardHeight = (configuration.screenHeightDp / 3 - 24).dp

    Card(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(percent = 25),
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight / 3 * 2)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 12.dp
                    ),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridCardItem("Built In Android      Package") {
    }
}