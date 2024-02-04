package com.ivanovictin.weatherapp.features.weather.ui.model

data class UiForecast(
    val astro: UiAstroForecast,
    val date: String,
    val dateEpoch: Int,
    val day: UiDayForecast,
    val hour: List<UiHourForecast>
)

data class UiAstroForecast(
    val timeOfDay: TimeOfDay,
    val moonIlluminationPercentage: Int,
    val moonPhase: String,
    val moonriseTime: String,
    val moonsetTime: String,
    val sunriseTime: String,
    val sunsetTime: String
)

data class UiDayForecast(
    val avghumidity: Int,
    val averageTempInCelsius: Double,
    val averageTempInFahrenheit: Double,
    val averageVisibilityKilometres: Double,
    val averageVisibilityMiles: Double,
    val condition: String?,
    val dailyChanceOfRainPercentage: Int,
    val dailyChanceOfSnowPercentage: Int,
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


data class UiHourForecast(
    val chanceOfRainPercentage: Int,
    val chanceOfSnowPercentage: Int,
    val cloud: Int,
    val condition: String?,
    val dewPointInCelsius: Double,
    val dewPointInFahrenheit: Double,
    val feelsLikeInCelsius: Double,
    val feelsLikeInFahrenheit: Double,
    val gustInKilometresPerHour: Double,
    val gustInMilesPerHour: Double,
    val heatIndexInCelsius: Double,
    val heatIndexInFahrenheit: Double,
    val humidity: Int,
    val isDay: Boolean,
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
    val willItRain: Boolean,
    val willItSnow: Boolean,
    val windDegree: Int,
    val windDirection: String,
    val windInKilometresPerHour: Double,
    val windInMilesPerHour: Double,
    val windChillInCelsius: Double,
    val windChillInFahrenheit: Double
)