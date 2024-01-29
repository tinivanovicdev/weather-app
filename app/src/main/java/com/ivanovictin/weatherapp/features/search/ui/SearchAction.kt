package com.ivanovictin.weatherapp.features.search.ui

sealed interface SearchAction {
    data class DestinationSelected(val destination: String) : SearchAction
    object SearchTapped : SearchAction
    data class QueryChanged(val query: String) : SearchAction
}