package com.ivanovictin.weatherapp.features.weather.data.mapper

import com.ivanovictin.weatherapp.features.weather.data.model.DayForecastResponse
import com.ivanovictin.weatherapp.features.weather.domain.model.DayForecast
import javax.inject.Inject

class DayForecastResponseToDayForecastMapper @Inject constructor() {
    fun map(origin: DayForecastResponse) : DayForecast {
        return with(origin) {
            DayForecast(
                avghumidity = avghumidity,
                averageTempInCelsius = averageTempInCelsius,
                averageTempInFahrenheit = averageTempInFahrenheit,
                averageVisibilityKilometres = averageVisibilityKilometres,
                averageVisibilityMiles = averageVisibilityMiles,
                condition = condition.status,
                dailyChanceOfRainPercentage = dailyChangeOfRainPercentage,
                dailyChanceOfSnowPercentage = dailyChangeOfSnowPercentage,
                dailyWillItRainPercentage = dailyWillItRainPercentage,
                dailyWillItSnowPercentage = dailyWillItSnowPercentage,
                maxTempInCelsius = maxTempInCelsius,
                maxTempInFahrenheit = maxTempInFahrenheit,
                maxWindInKilometresPerHour = maxWindInKilometresPerHour,
                maxWindInMilesPerHour = maxWindInMilesPerHour,
                minimalTempInCelsius = minimalTempInCelsius,
                minimalTempInFahrenheit = minimalTempInFahrenheit,
                totalPrecipitationInInches = totalPrecipitationInInches,
                totalPrecipitationInMillimetres = totalPrecipitationInMillimetres,
                totalSnowInCentimetres = totalSnowInCentimetres,
                uvIndex = uvIndex
            )
        }
    }
}