package com.ivanovictin.weatherapp.features.search.data

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.features.search.data.model.AutocompleteLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("v1/search.json")
    suspend fun getAutocompleteHints(
        @Query("q") locationName: String,
    ): EitherResult<List<AutocompleteLocationResponse>>
}