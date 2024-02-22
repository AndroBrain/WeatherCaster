package com.androbrain.weathercaster.ui.screen.forecast.details

import android.net.Uri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.androbrain.weathercaster.domain.forecast.model.ForecastModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val FORECAST_DETAILS_ROUTE = "FORECAST_DETAILS"
const val MODEL_ARG = "MODEL"

fun NavGraphBuilder.forecastDetailsScreen(
    navigateUp: () -> Unit,
) {
    composable("$FORECAST_DETAILS_ROUTE/{$MODEL_ARG}") {
        val viewModel: ForecastDetailsViewModel = hiltViewModel()
        ForecastDetailsScreen(viewModel = viewModel, navigateUp = navigateUp)
    }
}

fun NavController.navigateToForecastDetails(navOptions: NavOptions? = null, model: ForecastModel) {
    navigate("$FORECAST_DETAILS_ROUTE/${Uri.encode(Json.encodeToString(model))}", navOptions)
}

data class ForecastDetailsArgs(val model: ForecastModel) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        model = Json.decodeFromString(Uri.decode(checkNotNull(savedStateHandle[MODEL_ARG])))
    )
}
