package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class TempModel(
    val day: Double,
    val night: Double,
)
