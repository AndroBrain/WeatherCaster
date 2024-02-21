package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    val description: String,
    val icon: String,
)
