package com.androbrain.weathercaster.data.forecast

import android.icu.text.SimpleDateFormat
import android.text.format.DateFormat
import com.androbrain.weathercaster.data.forecast.model.response.FeelsLikeResponse
import com.androbrain.weathercaster.data.forecast.model.response.ForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.TempResponse
import com.androbrain.weathercaster.data.forecast.model.response.WeatherResponse
import com.androbrain.weathercaster.domain.forecast.model.FeelsLikeModel
import com.androbrain.weathercaster.domain.forecast.model.ForecastModel
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel
import com.androbrain.weathercaster.domain.forecast.model.TempModel
import com.androbrain.weathercaster.domain.forecast.model.WeatherModel
import java.util.*

private const val MILLIS_IN_SECOND = 1000
private const val DATE_FORMAT = "ddMM"
private const val TIME_FORMAT = "hhmm"

fun GetForecastResponse.toModel(latitude: Double, longitude: Double) = ForecastsModel(
    city = city.name,
    latitude = latitude,
    longitude = longitude,
    forecasts = list.map { it.toModel() }
)

fun ForecastResponse.toModel(): ForecastModel {
    val dateFormatter =
        SimpleDateFormat(
            DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT),
            Locale.getDefault(),
        )
    val timeFormatter =
        SimpleDateFormat(
            DateFormat.getBestDateTimePattern(Locale.getDefault(), TIME_FORMAT),
            Locale.getDefault(),
        )
    return ForecastModel(
        date = dateFormatter.format(Date(dt * MILLIS_IN_SECOND)),
        sunset = timeFormatter.format(Date(sunset * MILLIS_IN_SECOND)),
        sunrise = timeFormatter.format(Date(sunrise * MILLIS_IN_SECOND)),
        temp = temp.toModel(),
        weather = weather.map { it.toModel() },
        feelsLike = feelsLike.toModel(),
        humidity = humidity,
        pressure = pressure,
    )
}

fun TempResponse.toModel() = TempModel(
    day = day, night = night,
)

fun WeatherResponse.toModel() = WeatherModel(
    description = description, icon = icon,
)

fun FeelsLikeResponse.toModel() = FeelsLikeModel(
    day = day, night = night,
)
