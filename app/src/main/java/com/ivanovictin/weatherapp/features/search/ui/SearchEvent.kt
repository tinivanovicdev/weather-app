package com.ivanovictin.weatherapp.features.search.ui

sealed interface SearchEvent {
    data class NavigateToDestinationWeather(val destination: String) : SearchEvent
}