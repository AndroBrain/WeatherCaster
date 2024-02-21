package com.androbrain.weathercaster.data.di

import com.androbrain.weathercaster.data.api.WeatherForecastApiService
import com.androbrain.weathercaster.data.forecast.datasource.RemoteForecastDataSource
import com.androbrain.weathercaster.data.forecast.datasource.RetrofitRemoteForecastDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideRemoteForecastDataSource(
        api: WeatherForecastApiService,
    ): RemoteForecastDataSource = RetrofitRemoteForecastDataSource(api)
}