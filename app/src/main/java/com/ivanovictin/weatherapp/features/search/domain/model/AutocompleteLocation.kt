package com.ivanovictin.weatherapp.features.search.domain.model

data class AutocompleteLocation(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
)
