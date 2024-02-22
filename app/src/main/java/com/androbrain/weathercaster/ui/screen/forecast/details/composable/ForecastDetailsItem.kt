package com.androbrain.weathercaster.ui.screen.forecast.details.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun ForecastDetailsItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Row(modifier = modifier) {
        Text(text = label)
        Spacer(modifier = Modifier.width(App.dimens.viewSpacingSmall))
        Text(modifier = Modifier.weight(1F), text = value, textAlign = TextAlign.End)
    }
}
