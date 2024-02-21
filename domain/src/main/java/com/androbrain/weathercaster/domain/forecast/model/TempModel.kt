package com.androbrain.weathercaster.domain.forecast.model

import kotlinx.serialization.Serializable

@Serializable
data class TempModel(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double,
)
