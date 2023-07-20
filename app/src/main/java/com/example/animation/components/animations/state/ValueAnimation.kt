package com.example.animation.components.animations.state

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.components.PlayButton

@Composable
fun ValueAnimation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        SingleValueAnimation()
        Divider(modifier = Modifier.fillMaxWidth())
        MultipleValueAnimation()
    }
}

@Composable
fun SingleValueAnimation() {
    // you are animating two objects in the same time, it's better to use Multiple Value Animations Instead of Single Value
    var starting = remember {
        mutableStateOf(false)
    }
    val scale: Float by animateFloatAsState(
        targetValue = if (starting.value) 1f else .5f,
        animationSpec = tween(1000)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
    ) {
        AnimationLabel(title = "Single value animation")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(200.dp)
                .scale(scale)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(50.dp)
                ), contentAlignment = Alignment.Center
        )
        {
            PlayButton(starting = starting)
        }
    }
}

@Composable
fun MultipleValueAnimation() {
    var starting = remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(starting, label = "box state")
    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = createTransitionSpec(starting.value)
    ) {
        if (it.value) 1f else 0.2f
    }

    val color by transition.animateColor(
        label = "color", transitionSpec = createTransitionSpec(
            starting = starting.value
        )
    ) {
        if (it.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    }
    val borderColor by transition.animateColor(
        label = "border color",
        transitionSpec = createTransitionSpec(starting = starting.value)
    ) {
        if (it.value) Color.LightGray else MaterialTheme.colorScheme.secondary.copy(.7f)
    }
    val height = 60F to 200F
    val width = 60F to (height.second * 2)

    val rect by transition.animateRect(
        label = "rect", transitionSpec = createTransitionSpec(
            starting = starting.value
        )
    ) {
        if (it.value) Rect(0f, 0f, width.second, height.second) else Rect(
            0f,
            0f,
            width.first,
            height.first
        )
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimationLabel(title = "Multiple value animation")
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(rect.width.dp, rect.height.dp)
                .border(BorderStroke(1.dp, borderColor), shape = RoundedCornerShape(50.dp))
                .graphicsLayer(alpha = alpha)
                .background(
                    color,
                    shape = RoundedCornerShape(50.dp)
                ), contentAlignment = Alignment.Center
        ) {
            PlayButton(starting = starting)
        }
    }
}


@Composable
fun AnimationLabel(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun <T> createTransitionSpec(starting: Boolean): @Composable() (Transition.Segment<MutableState<Boolean>>.() -> FiniteAnimationSpec<T>) {
    return {
        if (starting) {
            tween(durationMillis = 1000)
        } else {
            spring(stiffness = 50f)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ValueAnimationPreview() {
    ValueAnimation()
}