package com.androbrain.weathercaster.ui.screen.locations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel

const val LOCATIONS_ROUTE = "LOCATIONS"

fun NavGraphBuilder.locationsScreen(
    navigateToForecast: (ForecastsModel) -> Unit,
) {
    composable(LOCATIONS_ROUTE) {
        val viewModel: LocationsViewModel = hiltViewModel()
        LocationsScreen(viewModel = viewModel, navigateToForecast = navigateToForecast)
    }
}

fun NavController.navigateToLocations(navOptions: NavOptions? = null) {
    navigate(LOCATIONS_ROUTE, navOptions)
}
