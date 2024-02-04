package com.ivanovictin.weatherapp.features.weather.domain.model

data class Weather(
    val country: String?,
    val location: String?,
    val temperatureInCelsius: Double?,
    val isDay: Boolean,
    val condition: String?,
    val pressureInMillibars: Double?,
    val windInKilometresPerHour: Double?,
    val cloudPercentage: Int?,
    val windDirection: String?,
    val humidity: String,
    val weatherImageCode: Int?,
    val forecast: List<Forecast>,
)
