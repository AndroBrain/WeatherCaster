package com.androbrain.weathercaster.domain.forecast.model

data class ForecastModel(
    val temp: TempModel,
    val weather: List<WeatherModel>,
)
