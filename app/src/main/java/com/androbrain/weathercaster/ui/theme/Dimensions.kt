package com.androbrain.weathercaster.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val screenSpacing: Dp = 16.dp,

    val viewSpacingSmall: Dp = 8.dp,
    val viewSpacingMedium: Dp = 16.dp,

    val weatherItemMinSize: Dp = 160.dp,
    val weatherIconSize: Dp = 50.dp,

    val buttonLoadingIndicatorSize: Dp = 24.dp,
)

val defaultDimensions = Dimensions()
