package com.androbrain.weathercaster.ui.screen.forecast.details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val FORECAST_DETAILS_ROUTE = "FORECAST_DETAILS"

fun NavGraphBuilder.forecastDetailsScreen(
    navigateUp: () -> Unit,
) {
    composable(FORECAST_DETAILS_ROUTE) {
        val viewModel: ForecastDetailsViewModel = hiltViewModel()
        ForecastDetailsScreen(viewModel = viewModel, navigateUp = navigateUp)
    }
}
