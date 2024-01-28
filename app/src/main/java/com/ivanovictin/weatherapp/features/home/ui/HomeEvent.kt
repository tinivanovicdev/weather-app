package com.ivanovictin.weatherapp.features.home.ui

sealed interface HomeEvent {
    data class ShowToast(val message: String) : HomeEvent
}
