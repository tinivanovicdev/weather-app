package com.ivanovictin.weatherapp.features.weather.domain

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.features.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherWithForecast(
        location: String,
        numberOfDays: Int,
        getAirQuality: String
    ): EitherResult<Weather>
}
