package com.ivanovictin.weatherapp.features.home.ui

sealed interface HomeAction {
    data class QueryChanged(val query: String) : HomeAction
    object QuerySubmitted : HomeAction
}
