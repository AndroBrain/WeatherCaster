package com.androbrain.weathercaster.ui.screen.forecast

import android.net.Uri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.ui.screen.forecast.details.ForecastDetailsArgs
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val FORECAST_ROUTE = "FORECAST"
private const val MODEL_ARG = "MODEL"

fun NavGraphBuilder.forecastScreen(
    navigateUp: () -> Unit,
    navigateToDetails: (ForecastDetailsArgs) -> Unit,
) {
    composable("$FORECAST_ROUTE/{$MODEL_ARG}") {
        val viewModel: ForecastViewModel = hiltViewModel()
        ForecastScreen(
            viewModel = viewModel,
            navigateUp = navigateUp,
            navigateToDetails = navigateToDetails,
        )
    }
}

fun NavController.navigateToForecast(navOptions: NavOptions? = null, model: ForecastsModel) {
    navigate("$FORECAST_ROUTE/${Uri.encode(Json.encodeToString(model))}", navOptions)
}

data class ForecastArgs(val model: ForecastsModel) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        model = Json.decodeFromString(Uri.decode(checkNotNull(savedStateHandle[MODEL_ARG])))
    )
}
