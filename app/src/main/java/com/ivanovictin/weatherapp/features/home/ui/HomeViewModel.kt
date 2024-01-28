package com.ivanovictin.weatherapp.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.common.di.ResourcesProvider
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.features.home.domain.mapper.WeatherToUiWeatherMapper
import com.ivanovictin.weatherapp.features.home.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val weatherToUiWeatherMapper: WeatherToUiWeatherMapper,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    val uiState = MutableStateFlow(HomeUiState.initialData)
    private val eventsChannel = Channel<HomeEvent>()
    val eventsFlow = eventsChannel.receiveAsFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.QuerySubmitted -> {
                viewModelScope.launch {
                    if (doesQueryMatchLocation()) {
                        return@launch
                    }

                    uiState.update { it.copy(isLoading = true) }
                    getCurrentWeatherUseCase.invoke(
                        locationName = uiState.value.query,
                        showAirQualityData = false,
                    ).either(::handleFailure) { weather ->
                        val uiWeather = weatherToUiWeatherMapper.map(weather)
                        uiState.update {
                            it.copy(
                                query = uiWeather.location,
                                uiWeather = weatherToUiWeatherMapper.map(weather),
                                isLoading = false
                            )
                        }
                    }
                }
            }
            is HomeAction.QueryChanged -> {
                uiState.update { it.copy(query = action.query) }
            }
        }
    }

    private fun doesQueryMatchLocation(): Boolean {
        return uiState.value.query.equals(uiState.value.uiWeather?.location, true)
    }

    private fun handleFailure(failure: Failure) {
        viewModelScope.launch {
            eventsChannel.send(
                when (failure) {
                    is Failure.ApiError -> {
                        HomeEvent.ShowToast(resourcesProvider.getString(R.string.location_not_found))
                    }
                    Failure.NetworkError -> {
                        HomeEvent.ShowToast(resourcesProvider.getString(R.string.check_your_internet_connection))
                    }
                    Failure.UnknownError -> {
                        HomeEvent.ShowToast(resourcesProvider.getString(R.string.error_general))
                    }
                }
            )
        }
        uiState.update { it.copy(isLoading = false) }
    }
}
