package com.androbrain.weathercaster.data.forecast.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TempResponse,
    @SerialName("feels_like")
    val feelsLike: FeelsLikeResponse,
    val weather: List<WeatherResponse>,
    val humidity: Double,
    val pressure: Double,
)
