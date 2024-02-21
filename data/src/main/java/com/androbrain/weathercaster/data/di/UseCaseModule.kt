package com.androbrain.weathercaster.data.di

import com.androbrain.weathercaster.domain.forecast.ForecastRepository
import com.androbrain.weathercaster.domain.forecast.GetForecastsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetForecastsUseCase(
        repository: ForecastRepository,
    ) = GetForecastsUseCase(repository)
}
