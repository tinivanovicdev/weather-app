package com.ivanovictin.weatherapp.features.home.data.mapper

import com.ivanovictin.weatherapp.features.home.data.model.HourForecastResponse
import com.ivanovictin.weatherapp.features.home.domain.model.HourForecast
import javax.inject.Inject

class HourForecastResponseToHourForecastMapper @Inject constructor(
    private val intToBooleanMapper: IntToBooleanMapper,
) {
    fun map(origin: HourForecastResponse): HourForecast {
        return with(origin) {
            HourForecast(
                chanceOfRainPercentage = chanceOfRainPercentage,
                chanceOfSnowPercentage = chanceOfSnowPercentage,
                cloud = cloud,
                condition = condition.status,
                dewPointInCelsius = dewPointInCelsius,
                dewPointInFahrenheit = dewPointInFahrenheit,
                feelsLikeInCelsius = feelsLikeInCelsius,
                feelsLikeInFahrenheit = feelsLikeInFahrenheit,
                gustInKilometresPerHour = gustInKilometresPerHour,
                gustInMilesPerHour = gustInMilesPerHour,
                heatIndexInCelsius = heatIndexInCelsius,
                heatIndexInFahrenheit = heatIndexInFahrenheit,
                humidity = humidity,
                isDay = intToBooleanMapper.map(isDay = isDay),
                precipitationInInches = precipitationInInches,
                precipitationInMillimetres = precipitationInMillimetres,
                pressureInInches = pressureInInches,
                pressureInMillibars = pressureInMillibars,
                snowInCentimetres = snowInCentimetres,
                temperatureInCelsius = temperatureInCelsius,
                temperatureInFahrenheit = temperatureInFahrenheit,
                time = time,
                timeEpoch = timeEpoch,
                uvIndex = uvIndex,
                visibilityInKilometres = visibilityInKilometres,
                visibilityInMiles = visibilityInMiles,
                willItRain = intToBooleanMapper.map(willItRain),
                willItSnow = intToBooleanMapper.map(willItSnow),
                windDegree = windDegree,
                windDirection = windDirection,
                windInKilometresPerHour = windInKilometresPerHour,
                windInMilesPerHour = windInMilesPerHour,
                windChillInCelsius = windChillInCelsius,
                windChillInFahrenheit = windChillInFahrenheit,
            )
        }
    }
}