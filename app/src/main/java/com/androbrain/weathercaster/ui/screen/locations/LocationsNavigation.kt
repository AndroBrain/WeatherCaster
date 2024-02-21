package com.androbrain.weathercaster.ui.screen.locations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val LOCATIONS_ROUTE = "LOCATIONS"

fun NavGraphBuilder.locationsScreen() {
    composable(LOCATIONS_ROUTE) {
        val viewModel: LocationsViewModel = hiltViewModel()
        LocationsScreen(viewModel = viewModel)
    }
}

fun NavController.navigateToLocations(navOptions: NavOptions? = null) {
    navigate(LOCATIONS_ROUTE, navOptions)
}
