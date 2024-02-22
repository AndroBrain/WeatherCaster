package com.androbrain.weathercaster.data.forecast.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val dt: Long,
    val temp: TempResponse,
    val weather: List<WeatherResponse>,
)