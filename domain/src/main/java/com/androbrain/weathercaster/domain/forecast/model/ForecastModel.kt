package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastModel(
    val date: String,
    val temp: TempModel,
    val weather: List<WeatherModel>,
)
