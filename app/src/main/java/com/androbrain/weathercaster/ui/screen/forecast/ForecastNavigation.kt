package com.androbrain.weathercaster.ui.screen.forecast

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val FORECAST_ROUTE = "FORECAST"

fun NavGraphBuilder.forecastScreen(
    navigateUp: () -> Unit,
) {
    composable(FORECAST_ROUTE) {
        val viewModel: ForecastViewModel = hiltViewModel()
        ForecastScreen(viewModel = viewModel, navigateUp = navigateUp)
    }
}

fun NavController.navigateToForecast(navOptions: NavOptions? = null) {
    navigate(FORECAST_ROUTE, navOptions)
}
