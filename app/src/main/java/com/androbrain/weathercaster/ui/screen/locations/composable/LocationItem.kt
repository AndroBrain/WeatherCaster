package com.androbrain.weathercaster.ui.screen.locations.composable

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.domain.core.ResultErrorType
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.ui.core.getMessage
import com.androbrain.weathercaster.ui.screen.locations.LocationDisplayable
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: LocationDisplayable,
    onRetry: () -> Unit,
    onClick: (ForecastsModel) -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Crossfade(targetState = location, label = "LocationCrossfade") {
            when (it) {
                is LocationDisplayable.Error -> LocationError(error = it.error, onClick = onRetry)
                is LocationDisplayable.Loading -> LocationLoading()
                is LocationDisplayable.Ok -> LocationOk(
                    location = it,
                    onClick = { onClick(it.model) },
                )
            }
        }
    }
}

@Composable
private fun LocationError(error: ResultErrorType, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(App.dimens.viewSpacingSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = error.getMessage()),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.height(App.dimens.viewSpacingSmall))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
            ),
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
private fun LocationLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(App.dimens.viewSpacingMedium)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun LocationOk(location: LocationDisplayable.Ok, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
            .padding(App.dimens.viewSpacingSmall),
        text = stringResource(
            id = R.string.location_label_precise,
            location.model.city,
            location.model.latitude,
            location.model.longitude
        )
    )
}

