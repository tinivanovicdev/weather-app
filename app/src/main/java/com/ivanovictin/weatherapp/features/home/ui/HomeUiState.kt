package com.ivanovictin.weatherapp.features.home.ui

import com.ivanovictin.weatherapp.features.home.ui.model.UiWeather

data class HomeUiState(
    val query: String,
    val isLoading: Boolean,
    val uiWeather: UiWeather?,
) {
    companion object {
        val initialData = HomeUiState(
            query = "",
            uiWeather = null,
            isLoading = false,
        )
    }
}
