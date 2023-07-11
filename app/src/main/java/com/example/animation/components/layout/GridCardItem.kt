package com.example.animation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridCardItem(
    value: String,
    cardSize: Pair<Dp, Dp>,
    CardImage: @Composable () -> Unit = {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(cardSize.second / 4 * 3 - 24.dp)
        )
    },
    onClick: () -> Unit
) {
    val (cardWidth, cardHeight) = cardSize
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
            CardImage()
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = value,
                modifier = Modifier
                    .padding(4.dp),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
