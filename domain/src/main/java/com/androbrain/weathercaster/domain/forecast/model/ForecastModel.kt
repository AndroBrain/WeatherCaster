package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastModel(
    val temp: TempModel,
    val weather: List<WeatherModel>,
)
