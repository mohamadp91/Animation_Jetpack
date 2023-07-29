package com.example.animation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animation.components.AppTopBar
import com.example.animation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierScreen(navController: NavController) {

    var sheetState =
        rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    var bottomSheetState = rememberBottomSheetScaffoldState(sheetState)
    val innerPadding = remember {
        mutableStateOf(8)
    }
    val outerPadding = remember {
        mutableStateOf(8)
    }
    val paddingFromBaseLine = remember {
        mutableStateOf(0)
    }
    val absolutePadding = remember {
        mutableStateOf(0)
    }
    val selectable = remember {
        mutableStateOf(false)
    }
    val toggleableState = remember(selectable.value) {
        mutableStateOf(!selectable.value)
    }

    var settingIntegerStates = listOf(
        "Inner padding" to innerPadding,
        "Outer padding" to outerPadding,
        "Padding from base line" to paddingFromBaseLine,
        "Absolute Padding" to absolutePadding,
    )
    var settingBooleanStates = listOf(
        "Selectable" to selectable,
        "Toggleable" to toggleableState
    )

    BottomSheetScaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                title = AppScreens.ModifierScreen.value
            )
        },
        scaffoldState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(settingIntegerStates, settingBooleanStates)
        },
        sheetPeekHeight = 128.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f),
                contentAlignment = Alignment.Center
            ) {
                val interactionModifier =
                    if (selectable.value) Modifier.selectableModifier(selectable) else Modifier.toggleableModifier(
                        toggleableState
                    )
                SimpleText(
                    modifier = interactionModifier
                        .animateContentSize()
                        .padding(outerPadding.value.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(percent = 25)
                        )
                        .padding(innerPadding.value.dp)
                        .paddingFromBaseline(paddingFromBaseLine.value.dp)
                        .absolutePadding(
                            left = absolutePadding.value.dp,
                            top = absolutePadding.value.dp,
                            bottom = absolutePadding.value.dp,
                            right = absolutePadding.value.dp,
                        )
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    settingsIntegers: List<Pair<String, MutableState<Int>>>,
    settingsBoolean: List<Pair<String, MutableState<Boolean>>>,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.3f)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        settingsIntegers.forEach {
            SliderLayout(it)
        }
        settingsBoolean.forEach {
            CheckBoxLayout(it)
        }
        OutlinedButton(
            onClick = {
                settingsIntegers.forEach {
                    it.second.value = 4
                }
                settingsBoolean.forEach {
                    it.second.value = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.error
            ),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.error),
            modifier = Modifier.wrapContentSize()
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "icons close",
                modifier = Modifier.padding(end = 6.dp),
            )
            Text(text = "Clear all")
        }
    }
}

@Composable
fun SliderLayout(
    settingFeature: Pair<String, MutableState<Int>>
) {
    val initialValue = 4
    Column(modifier = Modifier.padding(16.dp)) {
        Text(settingFeature.first, style = MaterialTheme.typography.titleMedium)
        Slider(
            value = settingFeature.second.value.toFloat(),
            onValueChange = {
                settingFeature.second.value = it.toInt()
            },
            steps = 16,
            valueRange = initialValue.toFloat()..64f
        )
    }

}

@Composable
fun CheckBoxLayout(
    settingFeature: Pair<String, MutableState<Boolean>>
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            settingFeature.first,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        Switch(checked = settingFeature.second.value,
            onCheckedChange = {
                settingFeature.second.value = it
            })
    }
}

@Composable
fun Modifier.selectableModifier(selectable: MutableState<Boolean>): Modifier {
    var selected by remember {
        mutableStateOf(false)
    }
    return this.selectable(
        selected = selected,
        enabled = selectable.value,
        interactionSource = MutableInteractionSource(),
        indication = LocalIndication.current,
        onClick = {
            selected = !selected
        })
}

@Composable
fun Modifier.toggleableModifier(toggleableState: MutableState<Boolean>): Modifier {
    var toggleable by remember {
        mutableStateOf(false)
    }
    return this.toggleable(toggleable,
        MutableInteractionSource(),
        LocalIndication.current,
        enabled = toggleableState.value,
        onValueChange = {
            toggleable = it
        })
}

@Composable
fun SimpleText(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .animateContentSize(),
        shape = RoundedCornerShape(percent = 25),
        color = MaterialTheme.colorScheme.secondary.copy(.7f)
    ) {
        Text(
            "This is a simple text",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierScreenPreview() {
    ModifierScreen(rememberNavController())
}