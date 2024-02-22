package com.androbrain.weathercaster.data.di

import android.content.Context
import com.androbrain.weathercaster.data.api.WeatherForecastApiService
import com.androbrain.weathercaster.data.forecast.datasource.RemoteForecastDataSource
import com.androbrain.weathercaster.data.forecast.datasource.RetrofitRemoteForecastDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideRemoteForecastDataSource(
        api: WeatherForecastApiService,
        @ApplicationContext context: Context,
    ): RemoteForecastDataSource = RetrofitRemoteForecastDataSource(api, context)
}
