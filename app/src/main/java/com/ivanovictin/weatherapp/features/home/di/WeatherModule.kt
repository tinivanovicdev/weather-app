package com.ivanovictin.weatherapp.features.home.di

import com.ivanovictin.weatherapp.common.di.KEY_WEATHER_API_RETROFIT_INSTANCE
import com.ivanovictin.weatherapp.features.home.data.WeatherApi
import com.ivanovictin.weatherapp.features.home.data.WeatherRepositoryImpl
import com.ivanovictin.weatherapp.features.home.data.mapper.WeatherResponseToWeatherMapper
import com.ivanovictin.weatherapp.features.home.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherApi(
        @Named(KEY_WEATHER_API_RETROFIT_INSTANCE) retrofit: Retrofit,
    ): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        weatherResponseToWeatherMapper: WeatherResponseToWeatherMapper,
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            weatherApi = weatherApi,
            weatherResponseToWeatherMapper = weatherResponseToWeatherMapper
        )
    }
}
