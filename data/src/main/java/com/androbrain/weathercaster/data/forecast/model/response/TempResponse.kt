package com.androbrain.weathercaster.data.forecast.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TempResponse(
    val day: Double,
    val night: Double,
)
