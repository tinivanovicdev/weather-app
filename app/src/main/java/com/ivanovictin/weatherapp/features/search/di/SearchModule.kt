package com.ivanovictin.weatherapp.features.search.di

import com.ivanovictin.weatherapp.common.di.KEY_WEATHER_API_RETROFIT_INSTANCE
import com.ivanovictin.weatherapp.features.search.data.SearchApi
import com.ivanovictin.weatherapp.features.search.data.SearchRepositoryImpl
import com.ivanovictin.weatherapp.features.search.data.mapper.AutocompleteLocationResponseToAutocompleteLocationMapper
import com.ivanovictin.weatherapp.features.search.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    fun provideSearchApi(
        @Named(KEY_WEATHER_API_RETROFIT_INSTANCE) retrofit: Retrofit,
    ): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    fun provideWeatherRepository(
        searchApi: SearchApi,
        mapper: AutocompleteLocationResponseToAutocompleteLocationMapper,
    ): SearchRepository {
        return SearchRepositoryImpl(
            searchApi = searchApi,
            mapper = mapper
        )
    }
}