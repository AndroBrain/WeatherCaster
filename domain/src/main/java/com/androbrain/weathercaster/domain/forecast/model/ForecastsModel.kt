package com.androbrain.weathercaster.domain.forecast.model

data class ForecastsModel(
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val forecasts: List<ForecastModel>,
)
