package com.ivanovictin.weatherapp.features.search.domain.mapper

import com.ivanovictin.weatherapp.features.search.domain.model.AutocompleteLocation
import com.ivanovictin.weatherapp.features.search.ui.model.UIAutocompleteLocation
import javax.inject.Inject

class AutocompleteLocationToUIAutocompleteLocationMapper @Inject constructor() {
    fun map(origin: List<AutocompleteLocation>): List<UIAutocompleteLocation> {
        return origin.map {
            UIAutocompleteLocation(id = it.id, name = it.name)
        }
    }
}