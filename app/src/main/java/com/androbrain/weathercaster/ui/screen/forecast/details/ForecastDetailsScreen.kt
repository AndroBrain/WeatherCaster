package com.androbrain.weathercaster.ui.screen.forecast.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.ui.screen.forecast.composable.ForecastItem
import com.androbrain.weathercaster.ui.screen.forecast.details.composable.ForecastDetailsItem
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun ForecastDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ForecastDetailsViewModel,
    navigateUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            id = R.string.location_label,
                            state.city,
                            state.latitude,
                            state.longitude,
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back),
                        )
                    }
                }
            )
        }
    ) { insets ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(App.dimens.viewSpacingSmall),
            contentPadding = PaddingValues(horizontal = App.dimens.screenSpacing),
        ) {
            val model = state.model
            item {
                ForecastItem(model = model)
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_day),
                    value = stringResource(id = R.string.temperature, model.temp.day),
                )
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_day_feels_like),
                    value = stringResource(id = R.string.temperature, model.feelsLike.day),
                )
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_night),
                    value = stringResource(id = R.string.temperature, model.temp.night),
                )
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_night_feels_like),
                    value = stringResource(id = R.string.temperature, model.feelsLike.day),
                )
            }
            item {
                HorizontalDivider()
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_humidity),
                    value = model.humidity.toString(),
                )
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_pressure),
                    value = model.pressure.toString(),
                )
            }
            item {
                HorizontalDivider()
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_sunrise),
                    value = model.sunrise,
                )
            }
            item {
                ForecastDetailsItem(
                    label = stringResource(id = R.string.forecast_details_sunset),
                    value = model.sunset,
                )
            }
        }
    }
}
