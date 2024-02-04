package com.ivanovictin.weatherapp.features.weather.data.mapper

import com.ivanovictin.weatherapp.features.weather.data.model.WeatherResponse
import com.ivanovictin.weatherapp.features.weather.domain.model.Weather
import javax.inject.Inject

class WeatherResponseToWeatherMapper @Inject constructor(
    private val intToBooleanMapper: IntToBooleanMapper,
    private val forecastResponseToForecastMapper: ForecastDayResponseToForecastMapper,
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
                isDay = intToBooleanMapper.map(currentWeather.isDay),
                humidity = currentWeather.humidityPercentage.toString(),
                weatherImageCode = currentWeather.weatherCondition?.code,
                forecast = forecastResponse.forecastDayResponse.map {
                    forecastResponseToForecastMapper.map(it)
                }
            )
        }
    }
}
