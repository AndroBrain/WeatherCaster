package com.androbrain.weathercaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.androbrain.weathercaster.ui.screen.forecast.details.forecastDetailsScreen
import com.androbrain.weathercaster.ui.screen.forecast.forecastScreen
import com.androbrain.weathercaster.ui.screen.forecast.navigateToForecast
import com.androbrain.weathercaster.ui.screen.locations.LOCATIONS_ROUTE
import com.androbrain.weathercaster.ui.screen.locations.locationsScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LOCATIONS_ROUTE,
    ) {
        locationsScreen()
        forecastScreen(navigateUp = navController::navigateUp)
        forecastDetailsScreen(navigateUp = navController::navigateUp)
    }
}
