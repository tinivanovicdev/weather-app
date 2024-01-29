package com.ivanovictin.weatherapp.features.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    val uiState = MutableStateFlow(SearchUiState.initialData)
    private val eventsChannel = Channel<SearchEvent>()
    val eventsFlow = eventsChannel.receiveAsFlow()


    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.DestinationSelected -> {
                // TODO:
            }

            is SearchAction.QueryChanged -> {
                uiState.update { it.copy(query = action.query) }
            }

            is SearchAction.SearchTapped -> {
                uiState.update { it.copy(isSearching = true) }
            }
        }
    }
}