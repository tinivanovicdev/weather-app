package com.ivanovictin.weatherapp.features.home.data

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.common.network.model.map
import com.ivanovictin.weatherapp.features.home.data.mapper.WeatherResponseToWeatherMapper
import com.ivanovictin.weatherapp.features.home.domain.WeatherRepository
import com.ivanovictin.weatherapp.features.home.domain.model.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherResponseToWeatherMapper: WeatherResponseToWeatherMapper,
) : WeatherRepository {
    override suspend fun getWeatherWithForecast(
        location: String,
        numberOfDays: Int,
        getAirQuality: String,
    ): EitherResult<Weather> {
        return weatherApi.getWeatherWithForecast(
            locationName = location,
            days = numberOfDays,
            airQualityDataFlag = getAirQuality
        ).map { weatherResponse ->
            weatherResponseToWeatherMapper.map(weatherResponse)
        }
    }
}
