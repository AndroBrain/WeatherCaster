package com.androbrain.weathercaster.ui.screen.forecast.details

import com.androbrain.weathercaster.domain.forecast.model.ForecastModel

data class ForecastDetailsState(
    val model: ForecastModel,
    val city: String,
    val longitude: Double,
    val latitude: Double,
)
