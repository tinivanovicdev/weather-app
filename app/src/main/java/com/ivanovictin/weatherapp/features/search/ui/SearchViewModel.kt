package com.ivanovictin.weatherapp.features.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.features.search.domain.mapper.AutocompleteLocationToUIAutocompleteLocationMapper
import com.ivanovictin.weatherapp.features.search.domain.usecase.GetAutocompleteHintsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAutocompleteHintsUseCase: GetAutocompleteHintsUseCase,
    private val mapper: AutocompleteLocationToUIAutocompleteLocationMapper,
) : ViewModel() {

    val uiState = MutableStateFlow(SearchUiState.initialData)
    private val eventsChannel = Channel<SearchEvent>()
    val eventsFlow = eventsChannel.receiveAsFlow()


    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.DestinationSelected -> {
                viewModelScope.launch {
                    eventsChannel.send(SearchEvent.NavigateToDestinationWeather(action.destination))
                }
            }

            is SearchAction.QueryChanged -> {
                viewModelScope.launch {
                    uiState.update { it.copy(query = action.query, wasSearchingInitiated = true) }
                    getAutocompleteHintsUseCase.invoke(action.query).either(::handleFailure) {
                        val locations = mapper.map(it)
                        uiState.update { it.copy(locations = locations) }
                    }
                }
            }

            is SearchAction.SearchTapped -> {
                uiState.update { it.copy(wasSearchingInitiated = true) }
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        // TODO:
    }
}