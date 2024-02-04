package com.ivanovictin.weatherapp.features.weather.ui

import com.ivanovictin.weatherapp.features.weather.ui.model.UiWeather

data class WeatherUiState(
    val query: String,
    val isLoading: Boolean,
    val uiWeather: UiWeather?,
) {
    companion object {
        val initialData = WeatherUiState(
            query = "",
            uiWeather = null,
            isLoading = false,
        )
    }
}
