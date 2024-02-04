package com.ivanovictin.weatherapp.features.weather.ui

sealed interface WeatherEvent {
    data class ShowToast(val message: String) : WeatherEvent
}
