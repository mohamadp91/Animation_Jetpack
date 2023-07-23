package com.example.animation.components.typography

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animation.R
import kotlinx.coroutines.delay

@Composable
fun TypographyBasics() {
    val textModifier = Modifier
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(50.dp)
        )
        .border(
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(50.dp)
        )
        .padding(16.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanAnnotatedStringText(textModifier)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        ParagraphAnnotatedStringText(textModifier)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        TextAnimatedLayout()
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun SpanAnnotatedStringText(modifier: Modifier) {
    val spanText = stringResource(R.string.span_text)
    val brushColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                spanText.forEachIndexed { index, c ->
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(brushColors),
                            fontFamily = if (index % 2 == 0) FontFamily.Cursive else FontFamily.Serif,
                            fontSize = 24.sp,
                            fontWeight = if (index % 2 == 0) FontWeight.Bold else FontWeight.Thin
                        )
                    ) {
                        append(c)
                    }
                }
            },
            modifier = modifier
        )
    }
}

@Composable
fun ParagraphAnnotatedStringText(modifier: Modifier) {

    val paragraphFa = stringResource(id = R.string.paragraph_text_fa)
    val paragraphEn = stringResource(id = R.string.paragraph_text)
    var isPersianText by remember {
        mutableStateOf(Locale.current.language == "fa")
    }
    val paragraphTextState = remember(isPersianText) {
        if (isPersianText) paragraphFa else paragraphEn
    }
    var visibleText by remember {
        mutableStateOf("")
    }
    var index by remember(visibleText) {
        mutableStateOf(visibleText.length)
    }
    LaunchedEffect(key1 = isPersianText) {
        if (index <= paragraphTextState.length) {
            paragraphTextState.forEachIndexed() { i, c ->
                delay(100)
                visibleText += c
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .animateContentSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    ParagraphStyle(
                        textAlign = TextAlign.Justify,
                        textDirection = if (isPersianText) TextDirection.Rtl else TextDirection.Ltr,
                        textIndent = if (isPersianText) TextIndent.None else TextIndent(
                            firstLine = 10.sp
                        ),
                        lineHeight = 24.sp
                    )
                ) {
                    withStyle(SpanStyle(fontSize = 18.sp)) {
                        this.append(visibleText)
                    }
                }
            },
            modifier = modifier.animateContentSize(),
        )
        TextButton(onClick = {
            isPersianText = !isPersianText
            visibleText = ""
        }) {
            Text(
                text = if (isPersianText) "Translate to En" else "Translate to Fa",
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextAnimatedLayout() {
    Surface(
        modifier = Modifier
            .size(150.dp, 70.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        val text = "This text is too long"
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .basicMarquee(
                    iterations = Int.MAX_VALUE,
                    animationMode = MarqueeAnimationMode.Immediately,
                    initialDelayMillis = 500,
                    delayMillis = 0
                ),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Visible
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TypographyBasicsPreview() {
    TypographyBasics()
}