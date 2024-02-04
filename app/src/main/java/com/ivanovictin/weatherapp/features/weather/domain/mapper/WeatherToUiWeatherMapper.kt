package com.ivanovictin.weatherapp.features.weather.domain.mapper

import com.ivanovictin.weatherapp.common.mapper.DoubleToCelsiusStringMapper
import com.ivanovictin.weatherapp.common.mapper.DoubleToKilometersPerHourStringMapper
import com.ivanovictin.weatherapp.common.mapper.StringToPercentageStringMapper
import com.ivanovictin.weatherapp.features.weather.domain.model.Weather
import com.ivanovictin.weatherapp.features.weather.ui.model.UiWeather
import com.ivanovictin.weatherapp.features.weather.ui.model.WeatherImage
import javax.inject.Inject

class WeatherToUiWeatherMapper @Inject constructor(
    private val forecastToUiForecastMapper: ForecastToUiForecastMapper,
    private val doubleToCelsiusStringMapper: DoubleToCelsiusStringMapper,
    private val doubleToKilometersPerHourStringMapper: DoubleToKilometersPerHourStringMapper,
    private val isDayBooleanToTimeOfDayMapper: IsDayBooleanToTimeOfDayMapper,
    private val stringToPercentageStringMapper: StringToPercentageStringMapper,
) {
    fun map(origin: Weather): UiWeather {
        return with(origin) {
            UiWeather(
                country = country.orEmpty(),
                location = location.orEmpty(),
                condition = condition.orEmpty(),
                weatherImage = WeatherImage.fromCode(weatherImageCode),
                currentTemperatureInCelsius = doubleToCelsiusStringMapper.map(temperatureInCelsius),
                pressure = pressureInMillibars.toString(),
                windSpeedInKmh = doubleToKilometersPerHourStringMapper.map(windInKilometresPerHour),
                windDirection = windDirection.orEmpty(),
                timeOfDay = isDayBooleanToTimeOfDayMapper.map(isDay),
                humidity = stringToPercentageStringMapper.map(humidity),
                forecast = forecast.map { forecastToUiForecastMapper.map(it) }
            )
        }
    }
}
