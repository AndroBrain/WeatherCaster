package com.androbrain.weathercaster.data.forecast

import com.androbrain.weathercaster.data.forecast.model.response.ForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.TempResponse
import com.androbrain.weathercaster.data.forecast.model.response.WeatherResponse
import com.androbrain.weathercaster.domain.forecast.model.ForecastModel
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.domain.forecast.model.TempModel
import com.androbrain.weathercaster.domain.forecast.model.WeatherModel

fun GetForecastResponse.toModel(latitude: Double, longitude: Double) = ForecastsModel(
    city = city.name,
    latitude = latitude,
    longitude = longitude,
    forecasts = list.map { it.toModel() }
)

fun ForecastResponse.toModel() = ForecastModel(
    temp = temp.toModel(),
    weather = weather.map { it.toModel() },
)

fun TempResponse.toModel() = TempModel(
    day = day, eve = eve, max = max, min = min, morn = morn, night = night,
)

fun WeatherResponse.toModel() = WeatherModel(
    description = description, icon = icon,
)
