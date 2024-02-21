package com.androbrain.weathercaster.data.api

import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val FORECAST_PATH = "forecast"

private const val LATITUDE_QUERY = "lat"
private const val LONGITUDE_QUERY = "lon"
private const val APP_ID_QUERY = "appid"

private const val API_KEY = "4aff0d93fc6fb6fd2fd195632dc9bbc1"

interface WeatherForecastApiService {
    @GET("$FORECAST_PATH/daily")
    suspend fun getDailyForecast(
        @Query(LATITUDE_QUERY) latitude: Double,
        @Query(LONGITUDE_QUERY) longitude: Double,
        @Query(APP_ID_QUERY) appId: String = API_KEY,
    ): Response<GetForecastResponse>
}
