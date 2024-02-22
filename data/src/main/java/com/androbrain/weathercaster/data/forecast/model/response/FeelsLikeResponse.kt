package com.androbrain.weathercaster.data.forecast.model.response

import kotlinx.serialization.Serializable

@Serializable
data class FeelsLikeResponse(
    val day: Double,
    val night: Double,
)
