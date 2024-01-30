package com.ivanovictin.weatherapp.features.search.data

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.common.network.model.map
import com.ivanovictin.weatherapp.features.search.data.mapper.AutocompleteLocationResponseToAutocompleteLocationMapper
import com.ivanovictin.weatherapp.features.search.domain.SearchRepository
import com.ivanovictin.weatherapp.features.search.domain.model.AutocompleteLocation
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val mapper: AutocompleteLocationResponseToAutocompleteLocationMapper,
) : SearchRepository {
    override suspend fun getAutocompleteHints(query: String): Either<Failure, List<AutocompleteLocation>> {
        return searchApi.getAutocompleteHints(query).map {
            mapper.map(it)
        }
    }

}