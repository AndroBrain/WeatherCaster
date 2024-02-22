package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class FeelsLikeModel(
    val day: Double,
    val night: Double,
)
