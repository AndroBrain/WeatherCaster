package com.androbrain.weathercaster.data.forecast

import android.icu.text.SimpleDateFormat
import android.text.format.DateFormat
import com.androbrain.weathercaster.data.forecast.model.response.ForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.TempResponse
import com.androbrain.weathercaster.data.forecast.model.response.WeatherResponse
import com.androbrain.weathercaster.domain.forecast.model.ForecastModel
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.domain.forecast.model.TempModel
import com.androbrain.weathercaster.domain.forecast.model.WeatherModel
import java.util.*

private const val MILLIS_IN_SECOND = 1000
private const val DATE_FORMAT = "ddMM"

fun GetForecastResponse.toModel(latitude: Double, longitude: Double) = ForecastsModel(
    city = city.name,
    latitude = latitude,
    longitude = longitude,
    forecasts = list.map { it.toModel() }
)

fun ForecastResponse.toModel(): ForecastModel {
    val formatter =
        SimpleDateFormat(
            DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT),
            Locale.getDefault(),
        )
    return ForecastModel(
        date = formatter.format(Date(dt * MILLIS_IN_SECOND)),
        temp = temp.toModel(),
        weather = weather.map { it.toModel() },
    )
}

fun TempResponse.toModel() = TempModel(
    day = day, eve = eve, max = max, min = min, morn = morn, night = night,
)

fun WeatherResponse.toModel() = WeatherModel(
    description = description, icon = icon,
)
