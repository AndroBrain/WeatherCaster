package com.androbrain.weathercaster.data.forecast.model.response


import kotlinx.serialization.Serializable

@Serializable
data class GetForecastResponse(
    val city: CityResponse,
    val list: List<ForecastResponse>,
)
