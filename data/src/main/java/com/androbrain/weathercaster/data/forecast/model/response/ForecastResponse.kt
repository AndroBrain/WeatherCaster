package com.androbrain.weathercaster.data.forecast.model.response


import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val temp: TempResponse,
    val weather: List<WeatherResponse>
)