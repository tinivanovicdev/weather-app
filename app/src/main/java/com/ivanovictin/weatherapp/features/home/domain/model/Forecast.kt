package com.ivanovictin.weatherapp.features.home.domain.model


import com.ivanovictin.weatherapp.features.home.data.model.WeatherConditionResponse


data class Forecast(
    val astro: AstroForecast,
    val date: String,
    val dateEpoch: Int,
    val day: DayForecast,
    val hour: List<HourForecast>
)

data class AstroForecast(
    val isMoonUp: Boolean,
    val isSunUp: Boolean,
    val moonIllumination: Int,
    val moonPhase: String,
    val moonriseTime: String,
    val moonsetTime: String,
    val sunriseTime: String,
    val sunsetTime: String
)

data class DayForecast(
    val avghumidity: Int,
    val averageTempInCelsius: Double,
    val averageTempInFahrenheit: Double,
    val averageVisibilityKilometres: Double,
    val averageVisibilityMiles: Double,
    val condition: String?,
    val dailyChangeOfRainPercentage: Int,
    val dailyChangeOfSnowPercentage: Int,
    val dailyWillItRainPercentage: Int,
    val dailyWillItSnowPercentage: Int,
    val maxTempInCelsius: Double,
    val maxTempInFahrenheit: Double,
    val maxWindInKilometresPerHour: Double,
    val maxWindInMilesPerHour: Double,
    val minimalTempInCelsius: Double,
    val minimalTempInFahrenheit: Double,
    val totalPrecipitationInInches: Double,
    val totalPrecipitationInMillimetres: Double,
    val totalSnowInCentimetres: Double,
    val uvIndex: Double
)


data class HourForecast(
    val chanceOfRainPercentage: Int,
    val chanceOfSnowPercentage: Int,
    val cloud: Int,
    val condition: WeatherConditionResponse,
    val dewPointInCelsius: Double,
    val dewPointInFahrenheit: Double,
    val feelsLikeInCelsius: Double,
    val feelsLikeInFahrenheit: Double,
    val gustInKilometresPerHour: Double,
    val gustInMilesPerHour: Double,
    val heatIndexInCelsius: Double,
    val heatIndexInFahrenheit: Double,
    val humidity: Int,
    val isDay: Int,
    val precipitationInInches: Double,
    val precipitationInMillimetres: Double,
    val pressureInInches: Double,
    val pressureInMillibars: Double,
    val snowInCentimetres: Double,
    val temperatureInCelsius: Double,
    val temperatureInFahrenheit: Double,
    val time: String,
    val timeEpoch: Int,
    val uvIndex: Double,
    val visibilityInKilometres: Double,
    val visibilityInMiles: Double,
    val willItRain: Int,
    val willItSnow: Int,
    val windDegree: Int,
    val windDirection: String,
    val windInKilometresPerHour: Double,
    val windInMilesPerHour: Double,
    val windChillInCelsius: Double,
    val windChillInFahrenheit: Double
)