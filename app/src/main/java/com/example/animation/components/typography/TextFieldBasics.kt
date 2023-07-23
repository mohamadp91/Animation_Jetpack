package com.example.animation.components.typography

import android.content.Context
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintManager
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.R

@Composable
fun TextFieldBasics() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MultiLineTextField()
    }
}

@OptIn(ExperimentalTextApi::class, ExperimentalAnimationApi::class)
@Composable
fun MultiLineTextField() {
    var textState by remember {
        mutableStateOf("")
    }
    var isTouched by remember {
        mutableStateOf(false)
    }
    var isError by remember(textState) {
        mutableStateOf(isTouched && textState.trim().isEmpty())
    }
    var passwordVisual by remember {
        mutableStateOf(false)
    }

    val brush = remember {
        Brush.linearGradient(
            colors = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)
        )
    }
    val errorColor = MaterialTheme.colorScheme.error

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
            },
            textStyle = TextStyle(brush = brush),
            label = {
                Text(text = "Enter text")
            },
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .onFocusChanged {
                    if (!isTouched)
                        isTouched = true
                },
            leadingIcon = {
                val context = LocalContext.current
                Icon(
                    painter = painterResource(id = R.drawable.print),
                    contentDescription = "print",
                    modifier = Modifier
                        .clickable(MutableInteractionSource(),
                            enabled = textState.trim().isNotEmpty(),
                            indication = null,
                            onClick = {
                                onPrintClicked(context, textState)
                            })
                )
            },
            trailingIcon = {
                var trailingIconId = remember(passwordVisual) {
                    if (passwordVisual) R.drawable.outline_lock_24 else R.drawable.outline_lock_open_24
                }
                AnimatedContent(targetState = trailingIconId,
                    transitionSpec = {
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Up) with slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Down
                        )
                    }) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "text field",
                        modifier = Modifier.clickable(interactionSource = MutableInteractionSource(),
                            indication = null, onClick = {
                                passwordVisual = !passwordVisual
                            })
                    )
                }
            },
            singleLine = false,
            shape = RoundedCornerShape(
                topStartPercent = 5,
                bottomStartPercent = 25,
                bottomEndPercent = 25,
                topEndPercent = 25
            ),
            visualTransformation = if (passwordVisual) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                errorLabelColor = errorColor,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary.copy(.7f),
                focusedTextColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary.copy(.7f),
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true
            ),
            isError = isError
        )
    }
}

private fun onPrintClicked(
    context: Context,
    textState: String
) {
    Toast.makeText(context, "Let's print your text", Toast.LENGTH_LONG).show()
    // Get a PrintManager instance
    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
    // Set job name, which will be displayed in the print queue
    val jobName = "${context.getString(R.string.app_name)} Document"
    // Start a print job, passing in a PrintDocumentAdapter implementation
    // to handle the generation of a print document
    val adapter = object : PrintDocumentAdapter() {
        override fun onStart() {
            super.onStart()
        }

        override fun onLayout(
            p0: PrintAttributes?,
            p1: PrintAttributes?,
            p2: CancellationSignal?,
            p3: LayoutResultCallback?,
            p4: Bundle?
        ) {
        }

        override fun onWrite(
            p0: Array<out PageRange>?,
            p1: ParcelFileDescriptor?,
            p2: CancellationSignal?,
            p3: WriteResultCallback?
        ) {
        }

        override fun onFinish() {
            super.onFinish()
        }
    }
    printManager.print(jobName, adapter, null)
}

@Preview(showBackground = true)
@Composable
fun TextFieldBasicPreview() {
    TextFieldBasics()
}
