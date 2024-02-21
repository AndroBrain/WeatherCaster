package com.androbrain.weathercaster.data.forecast.datasource

import com.androbrain.weathercaster.data.api.WeatherForecastApiService
import com.androbrain.weathercaster.data.apiCall
import com.androbrain.weathercaster.data.forecast.model.request.GetForecastRequest

class RetrofitRemoteForecastDataSource(
    private val api: WeatherForecastApiService,
) : RemoteForecastDataSource {
    override suspend fun getForecast(request: GetForecastRequest) = apiCall {
        api.getDailyForecast(
            latitude = request.latitude,
            longitude = request.longitude,
            appid = "4aff0d93fc6fb6fd2fd195632dc9bbc1"
        )
    }
}
