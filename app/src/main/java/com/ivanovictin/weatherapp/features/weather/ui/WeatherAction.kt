package com.ivanovictin.weatherapp.features.weather.ui

sealed interface WeatherAction {
    data class DayClicked(val index: Int) : WeatherAction
}