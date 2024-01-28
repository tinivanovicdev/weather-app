package com.ivanovictin.weatherapp.features.home.domain.mapper

import com.ivanovictin.weatherapp.common.mapper.DoubleToCelsiusStringMapper
import com.ivanovictin.weatherapp.common.mapper.DoubleToKilometersPerHourStringMapper
import com.ivanovictin.weatherapp.common.mapper.StringToPercentageStringMapper
import com.ivanovictin.weatherapp.features.home.domain.model.Weather
import com.ivanovictin.weatherapp.features.home.ui.model.UiWeather
import com.ivanovictin.weatherapp.features.home.ui.model.WeatherImage
import javax.inject.Inject

class WeatherToUiWeatherMapper @Inject constructor(
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
            )
        }
    }
}
