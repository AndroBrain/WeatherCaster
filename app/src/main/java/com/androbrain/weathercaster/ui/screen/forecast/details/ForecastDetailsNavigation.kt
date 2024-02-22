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
private const val MODEL_ARG = "MODEL"
private const val CITY_ARG = "CITY"
private const val LATITUDE_ARG = "LATITUDE"
private const val LONGITUDE_ARG = "LONGITUDE"

fun NavGraphBuilder.forecastDetailsScreen(
    navigateUp: () -> Unit,
) {
    composable("$FORECAST_DETAILS_ROUTE/{$MODEL_ARG}/{$CITY_ARG}/{$LATITUDE_ARG}/{$LONGITUDE_ARG}") {
        val viewModel: ForecastDetailsViewModel = hiltViewModel()
        ForecastDetailsScreen(viewModel = viewModel, navigateUp = navigateUp)
    }
}

fun NavController.navigateToForecastDetails(
    navOptions: NavOptions? = null,
    args: ForecastDetailsArgs,
) {
    navigate(
        "$FORECAST_DETAILS_ROUTE/${Uri.encode(Json.encodeToString(args.model))}/${args.city}/${args.latitude}/${args.longitude}",
        navOptions
    )
}

data class ForecastDetailsArgs(
    val model: ForecastModel,
    val city: String,
    val latitude: Double,
    val longitude: Double,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        model = Json.decodeFromString(Uri.decode(checkNotNull(savedStateHandle[MODEL_ARG]))),
        city = checkNotNull(savedStateHandle[CITY_ARG]) as String,
        latitude = (checkNotNull(savedStateHandle[LATITUDE_ARG]) as String).toDouble(),
        longitude = (checkNotNull(savedStateHandle[LONGITUDE_ARG]) as String).toDouble(),
    )
}
