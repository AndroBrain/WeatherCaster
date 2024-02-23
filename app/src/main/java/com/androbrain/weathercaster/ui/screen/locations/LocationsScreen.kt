package com.androbrain.weathercaster.ui.screen.locations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.ui.screen.locations.composable.AddLocationItem
import com.androbrain.weathercaster.ui.screen.locations.composable.LocationItem
import com.androbrain.weathercaster.ui.theme.App

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel,
    navigateToForecast: (ForecastsModel) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        }
    ) { insets ->
        val locationPadding = PaddingValues(bottom = App.dimens.viewSpacingSmall)
        LazyColumn(
            modifier = Modifier.padding(insets),
            contentPadding = PaddingValues(horizontal = App.dimens.screenSpacing)
        ) {
            item {
                AddLocationItem(
                    latitude = state.latitude,
                    longitude = state.longitude,
                    locationLoading = state.loadingLocation,
                    addError = state.addError,
                    setLatitude = viewModel::setLatitude,
                    setLongitude = viewModel::setLongitude,
                    setLoadingLocation = viewModel::setLoadingLocation,
                    add = viewModel::addLocation,
                )
            }
            item {
                Spacer(modifier = Modifier.height(App.dimens.viewSpacingMedium))
            }
            val locationModifier = Modifier
                .fillMaxWidth()
                .padding(locationPadding)
            items(items = state.locations, key = { it.id }) { location ->
                LocationItem(
                    modifier = locationModifier,
                    location = location,
                    onClick = { model -> navigateToForecast(model) },
                    onRetry = { viewModel.retry(location) },
                )
            }
        }
    }
}
