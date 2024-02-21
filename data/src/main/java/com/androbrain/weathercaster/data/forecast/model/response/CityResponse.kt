package com.androbrain.weathercaster.data.forecast.model.response


import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val name: String,
)