package com.example.animation.util

import android.content.res.Configuration


fun getScreenSize(configuration: Configuration) =
    Pair(configuration.screenWidthDp, configuration.screenHeightDp)