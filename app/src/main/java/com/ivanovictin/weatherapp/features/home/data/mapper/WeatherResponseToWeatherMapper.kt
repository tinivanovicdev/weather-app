package com.ivanovictin.weatherapp.features.home.data.mapper

import com.ivanovictin.weatherapp.features.home.data.model.WeatherResponse
import com.ivanovictin.weatherapp.features.home.domain.model.Weather
import javax.inject.Inject

class WeatherResponseToWeatherMapper @Inject constructor(
    private val isDayIntMapperToBooleanMapper: IsDayIntMapperToBooleanMapper,
) {
    fun map(origin: WeatherResponse): Weather {
        return with(origin) {
            Weather(
                country = location.country,
                location = location.name,
                temperatureInCelsius = currentWeather.temperatureInCelsius,
                condition = currentWeather.weatherCondition?.status,
                windInKilometresPerHour = currentWeather.windSpeedInKilometersPerHour,
                cloudPercentage = currentWeather.cloudPercentage,
                pressureInMillibars = currentWeather.pressureInMillibars,
                windDirection = currentWeather.windDirection,
                isDay = isDayIntMapperToBooleanMapper.map(currentWeather.isDay),
                humidity = currentWeather.humidityPercentage.toString(),
                weatherImageCode = currentWeather.weatherCondition?.code
            )
        }
    }
}
