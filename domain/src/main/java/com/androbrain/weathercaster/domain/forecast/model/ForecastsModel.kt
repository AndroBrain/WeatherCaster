package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastsModel(
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val forecasts: List<ForecastModel>,
)
