package com.ivanovictin.weatherapp.features.search.domain

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.features.search.domain.model.AutocompleteLocation

interface SearchRepository {

    suspend fun getAutocompleteHints(query: String): Either<Failure, List<AutocompleteLocation>>
}