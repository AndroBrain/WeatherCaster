package com.androbrain.weathercaster.data.api

import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val FORECAST_PATH = "forecast"

private const val LATITUDE_QUERY = "lat"
private const val LONGITUDE_QUERY = "lon"

interface WeatherForecastApiService {
    @GET("$FORECAST_PATH/daily")
    suspend fun getDailyForecast(
        @Query(LATITUDE_QUERY) latitude: Double,
        @Query(LONGITUDE_QUERY) longitude: Double,
        @Query("appid") appid: String,
    ): Response<GetForecastResponse>
}
