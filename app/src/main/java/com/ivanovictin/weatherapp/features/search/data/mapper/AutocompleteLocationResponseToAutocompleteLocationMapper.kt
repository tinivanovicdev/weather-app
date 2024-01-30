package com.ivanovictin.weatherapp.features.search.data.mapper

import com.ivanovictin.weatherapp.features.search.data.model.AutocompleteLocationResponse
import com.ivanovictin.weatherapp.features.search.domain.model.AutocompleteLocation
import javax.inject.Inject

class AutocompleteLocationResponseToAutocompleteLocationMapper @Inject constructor() {

    fun map(origin: List<AutocompleteLocationResponse>): List<AutocompleteLocation> {
        return origin.map {
            AutocompleteLocation(
                id = it.id,
                name = it.name,
                region = it.region,
                country = it.country,
            )
        }
    }
}