package com.androbrain.weathercaster.domain.forecast

import com.androbrain.weathercaster.domain.core.UseCaseResult
import com.androbrain.weathercaster.domain.forecast.model.ForecastsModel

interface ForecastRepository {
    suspend fun getForecasts(latitude: Double, longitude: Double): UseCaseResult<ForecastsModel>
}
