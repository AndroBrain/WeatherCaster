package com.androbrain.weathercaster.ui.screen.locations

import com.androbrain.weathercaster.domain.core.ResultErrorType
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel

sealed class LocationDisplayable(
    val id: String,
    val latitude: Double,
    val longitude: Double,
) {
    class Loading(id: String, latitude: Double, longitude: Double) :
        LocationDisplayable(id = id, latitude = latitude, longitude = longitude)

    class Error(id: String, latitude: Double, longitude: Double, val error: ResultErrorType) :
        LocationDisplayable(id = id, latitude = latitude, longitude = longitude)

    class Ok(id: String, latitude: Double, longitude: Double, val model: ForecastsModel) :
        LocationDisplayable(id = id, latitude = latitude, longitude = longitude)
}
