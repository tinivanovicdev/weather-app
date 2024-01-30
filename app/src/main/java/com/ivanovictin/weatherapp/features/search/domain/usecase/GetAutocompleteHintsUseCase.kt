package com.ivanovictin.weatherapp.features.search.domain.usecase

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.features.search.domain.SearchRepository
import com.ivanovictin.weatherapp.features.search.domain.model.AutocompleteLocation
import javax.inject.Inject

class GetAutocompleteHintsUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) : suspend (String) -> EitherResult<List<AutocompleteLocation>> {
    override suspend fun invoke(query: String): Either<Failure, List<AutocompleteLocation>> {
        return searchRepository.getAutocompleteHints(query = query)
    }
}