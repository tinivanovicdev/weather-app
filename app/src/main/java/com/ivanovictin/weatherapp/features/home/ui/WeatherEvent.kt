package com.ivanovictin.weatherapp.features.home.ui

sealed interface WeatherEvent {
    data class ShowToast(val message: String) : WeatherEvent
}
