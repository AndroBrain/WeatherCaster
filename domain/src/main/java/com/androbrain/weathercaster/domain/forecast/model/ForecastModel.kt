package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastModel(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val temp: TempModel,
    val feelsLike: FeelsLikeModel,
    val weather: List<WeatherModel>,
    val humidity: Double,
    val pressure: Double,
)
