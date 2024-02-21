package com.androbrain.weathercaster.data.di

import android.content.Context
import com.androbrain.weathercaster.data.R
import com.androbrain.weathercaster.data.api.WeatherForecastApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        prettyPrint = false
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(
                context.resources.getInteger(R.integer.api_connect_timeout).toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                context.resources.getInteger(R.integer.api_read_timeout).toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideApiService(
        @ApplicationContext context: Context,
        json: Json,
        okHttpClient: OkHttpClient,
    ): WeatherForecastApiService = Retrofit.Builder()
        .baseUrl(context.getString(R.string.weather_forecast_api_url))
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(WeatherForecastApiService::class.java)
}