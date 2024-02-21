package com.androbrain.weathercaster.data.forecast.model.response


import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val description: String,
    val icon: String,
)