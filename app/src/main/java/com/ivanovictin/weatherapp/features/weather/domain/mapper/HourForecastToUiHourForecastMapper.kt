package com.ivanovictin.weatherapp.features.weather.domain.mapper

import com.ivanovictin.weatherapp.features.weather.domain.model.HourForecast
import com.ivanovictin.weatherapp.features.weather.ui.model.UiHourForecast
import javax.inject.Inject

class HourForecastToUiHourForecastMapper @Inject constructor() {
    fun map(origin: HourForecast): UiHourForecast {
        return with(origin) {
            UiHourForecast(
                chanceOfRainPercentage = chanceOfRainPercentage,
                chanceOfSnowPercentage = chanceOfSnowPercentage,
                cloud = cloud,
                condition = condition,
                dewPointInCelsius = dewPointInCelsius,
                dewPointInFahrenheit = dewPointInFahrenheit,
                feelsLikeInCelsius = feelsLikeInCelsius,
                feelsLikeInFahrenheit = feelsLikeInFahrenheit,
                gustInKilometresPerHour = gustInKilometresPerHour,
                gustInMilesPerHour = gustInMilesPerHour,
                heatIndexInCelsius = heatIndexInCelsius,
                heatIndexInFahrenheit = heatIndexInFahrenheit,
                humidity = humidity,
                isDay = isDay,
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
                willItRain = willItRain,
                willItSnow = willItSnow,
                windDegree = windDegree,
                windDirection = windDirection,
                windInKilometresPerHour = windInKilometresPerHour,
                windInMilesPerHour = windInMilesPerHour,
                windChillInCelsius = windChillInCelsius,
                windChillInFahrenheit = windChillInFahrenheit

            )
        }
    }
}