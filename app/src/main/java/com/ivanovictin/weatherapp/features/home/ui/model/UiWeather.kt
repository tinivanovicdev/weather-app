package com.ivanovictin.weatherapp.features.home.ui.model

data class UiWeather(
    val country: String,
    val location: String,
    val condition: String,
    val currentTemperatureInCelsius: String,
    val pressure: String,
    val windSpeedInKmh: String,
    val humidity: String,
    val windDirection: String,
    val timeOfDay: TimeOfDay,
    val weatherImage: WeatherImage,
)
