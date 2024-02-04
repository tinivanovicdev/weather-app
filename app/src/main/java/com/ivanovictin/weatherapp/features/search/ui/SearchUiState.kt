package com.ivanovictin.weatherapp.features.search.ui

import com.ivanovictin.weatherapp.features.search.ui.model.UIAutocompleteLocation

data class SearchUiState(
    val query: String,
    val isSearching: Boolean,
    val locations: List<UIAutocompleteLocation>,
) {
    companion object {
        val initialData = SearchUiState(
            query = "",
            isSearching = false,
            locations = emptyList()
        )
    }
}