package com.androbrain.weathercaster.data.di

import com.androbrain.weathercaster.data.forecast.datasource.RemoteForecastDataSource
import com.androbrain.weathercaster.data.forecast.DefaultForecastRepository
import com.androbrain.weathercaster.domain.forecast.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideForecastRepository(
        remoteForecastDataSource: RemoteForecastDataSource,
    ): ForecastRepository = DefaultForecastRepository(remoteForecastDataSource)
}
