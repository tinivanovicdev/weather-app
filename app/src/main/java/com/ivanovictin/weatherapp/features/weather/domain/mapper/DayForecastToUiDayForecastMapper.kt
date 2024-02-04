package com.ivanovictin.weatherapp.features.weather.domain.mapper

import com.ivanovictin.weatherapp.features.weather.domain.model.DayForecast
import com.ivanovictin.weatherapp.features.weather.ui.model.UiDayForecast
import javax.inject.Inject

class DayForecastToUiDayForecastMapper @Inject constructor() {

    fun map(origin: DayForecast): UiDayForecast {
        return with(origin) {
            UiDayForecast(
                avghumidity = avghumidity,
                averageTempInCelsius = averageTempInCelsius,
                averageTempInFahrenheit = averageTempInFahrenheit,
                averageVisibilityKilometres = averageVisibilityKilometres,
                averageVisibilityMiles = averageVisibilityMiles,
                condition = condition,
                dailyChanceOfRainPercentage = dailyChanceOfRainPercentage,
                dailyChanceOfSnowPercentage = dailyChanceOfSnowPercentage,
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