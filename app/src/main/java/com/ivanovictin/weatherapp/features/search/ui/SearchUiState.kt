package com.ivanovictin.weatherapp.features.search.ui

import com.ivanovictin.weatherapp.features.search.ui.model.UIAutocompleteLocation

data class SearchUiState(
    val query: String,
    val wasSearchingInitiated: Boolean,
    val isSearching: Boolean,
    val locations: List<UIAutocompleteLocation>,
) {
    companion object {
        val initialData = SearchUiState(
            query = "",
            wasSearchingInitiated = false,
            isSearching = false,
            locations = emptyList()
        )
    }
}