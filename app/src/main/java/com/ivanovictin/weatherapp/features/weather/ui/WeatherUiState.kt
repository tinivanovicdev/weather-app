package com.ivanovictin.weatherapp.features.weather.ui

import com.ivanovictin.weatherapp.features.weather.ui.model.UiWeather

data class WeatherUiState(
    val isLoading: Boolean,
    val uiWeather: UiWeather?,
    val selectedDayIndex: Int,
) {
    companion object {
        val initialData = WeatherUiState(
            uiWeather = null,
            isLoading = false,
            selectedDayIndex = -1
        )
    }
}
