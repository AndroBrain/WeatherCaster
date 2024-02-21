package com.androbrain.weathercaster.data.forecast

import com.androbrain.weathercaster.data.ApiResponse
import com.androbrain.weathercaster.data.forecast.datasource.RemoteForecastDataSource
import com.androbrain.weathercaster.data.forecast.model.request.GetForecastRequest
import com.androbrain.weathercaster.domain.core.ResultErrorType
import com.androbrain.weathercaster.domain.core.UseCaseResult
import com.androbrain.weathercaster.domain.forecast.ForecastRepository
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel

class DefaultForecastRepository(
    private val dataSource: RemoteForecastDataSource,
) : ForecastRepository {
    override suspend fun getForecasts(
        latitude: Double,
        longitude: Double,
    ): UseCaseResult<ForecastsModel> =
        when (
            val response = dataSource.getForecast(
                GetForecastRequest(latitude = latitude, longitude = longitude)
            )
        ) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(
                response.body.toModel(latitude = latitude, longitude = longitude)
            )
        }
}
