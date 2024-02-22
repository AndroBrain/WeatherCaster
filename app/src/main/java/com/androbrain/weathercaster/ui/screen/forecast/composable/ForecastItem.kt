package com.androbrain.weathercaster.ui.screen.forecast.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.domain.forecast.model.ForecastModel
import com.androbrain.weathercaster.ui.theme.App
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    model: ForecastModel,
    onClick: (() -> Unit)? = null,
) {
    val onClickModifier = if (onClick == null) {
        Modifier
    } else {
        Modifier.clickable(onClick = onClick)
    }
    OutlinedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(onClickModifier)
                .padding(App.dimens.viewSpacingSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.temperature, model.temp.day),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(App.dimens.viewSpacingSmall),
            ) {
                model.weather.forEach { weather ->
                    GlideImage(
                        modifier = Modifier.size(App.dimens.weatherIconSize),
                        imageModel = { weather.icon },
                    )
                }
            }
            Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
            Text(text = model.date, style = MaterialTheme.typography.titleMedium)
        }
    }
}
