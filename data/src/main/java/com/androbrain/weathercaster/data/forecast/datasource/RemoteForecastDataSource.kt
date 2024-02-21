package com.androbrain.weathercaster.data.forecast.datasource

import com.androbrain.weathercaster.data.ApiResponse
import com.androbrain.weathercaster.data.forecast.model.request.GetForecastRequest
import com.androbrain.weathercaster.data.forecast.model.response.GetForecastResponse

interface RemoteForecastDataSource {
    suspend fun getForecast(request: GetForecastRequest): ApiResponse<GetForecastResponse>
}
