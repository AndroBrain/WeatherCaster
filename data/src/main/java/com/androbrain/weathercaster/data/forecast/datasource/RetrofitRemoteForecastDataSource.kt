package com.androbrain.weathercaster.data.forecast.datasource

import android.content.Context
import com.androbrain.weathercaster.data.ApiResponse
import com.androbrain.weathercaster.data.R
import com.androbrain.weathercaster.data.api.WeatherForecastApiService
import com.androbrain.weathercaster.data.apiCall
import com.androbrain.weathercaster.data.forecast.model.request.GetForecastRequest
import com.androbrain.weathercaster.data.forecast.model.response.ForecastResponse
import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse

class RetrofitRemoteForecastDataSource(
    private val api: WeatherForecastApiService,
    private val context: Context,
) : RemoteForecastDataSource {
    override suspend fun getForecast(request: GetForecastRequest): ApiResponse<GetForecastResponse> {
        val response = apiCall {
            api.getDailyForecast(
                latitude = request.latitude,
                longitude = request.longitude,
            )
        }
        return if (response is ApiResponse.Ok) {
            val list = response.body.list.map { forecast ->
                ForecastResponse(
                    forecast.dt,
                    forecast.temp,
                    weather = forecast.weather.map {
                        it.copy(
                            icon = context.getString(
                                R.string.weather_forecast_api_icon_formatter,
                                it.icon,
                            )
                        )
                    }
                )
            }
            response.copy(body = response.body.copy(list = list))
        } else {
            response
        }
    }
}
