package com.androbrain.weathercaster.domain.forecast

class GetForecastsUseCase(
    private val repository: ForecastRepository,
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) =
        repository.getForecasts(latitude, longitude)
}
