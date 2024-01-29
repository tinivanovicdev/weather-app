package com.ivanovictin.weatherapp.features.search.ui

import com.ivanovictin.weatherapp.features.search.ui.model.UILocationItem

data class SearchUiState(
    val query: String,
    val isSearching: Boolean,
    val locations: List<UILocationItem>,
) {
    companion object {
        val initialData = SearchUiState(query = "", isSearching = false, locations = emptyList())
    }
}