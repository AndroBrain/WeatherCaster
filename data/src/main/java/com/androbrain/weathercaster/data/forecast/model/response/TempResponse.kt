package com.androbrain.weathercaster.data.forecast.model.response


import kotlinx.serialization.Serializable

@Serializable
data class TempResponse(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)
