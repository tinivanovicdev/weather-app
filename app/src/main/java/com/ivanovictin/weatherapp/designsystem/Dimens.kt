package com.ivanovictin.weatherapp.designsystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val xxSmall: Dp,
    val xSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val xLarge: Dp,
    val xxLarge: Dp,
    val huge: Dp,
)

val compactDimensions = Dimens(
    xxSmall = 4.dp,
    xSmall = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    large = 20.dp,
    xLarge = 24.dp,
    xxLarge = 28.dp,
    huge = 48.dp,
)

val LocalDimens = compositionLocalOf { compactDimensions }
