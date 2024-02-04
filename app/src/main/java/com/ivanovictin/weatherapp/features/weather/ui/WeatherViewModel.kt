package com.ivanovictin.weatherapp.features.weather.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.common.di.ResourcesProvider
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.common.utils.NavigationUtils.DESTINATION
import com.ivanovictin.weatherapp.features.weather.domain.mapper.WeatherToUiWeatherMapper
import com.ivanovictin.weatherapp.features.weather.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val weatherToUiWeatherMapper: WeatherToUiWeatherMapper,
    private val resourcesProvider: ResourcesProvider,
) : ViewModel() {

    val uiState = MutableStateFlow(WeatherUiState.initialData)
    private val eventsChannel = Channel<WeatherEvent>()

    init {
        viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }
            getCurrentWeatherUseCase.invoke(
                locationName = savedStateHandle.get<String>(DESTINATION) ?: "",
                numberOfDays = 7,
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

    private fun handleFailure(failure: Failure) {
        viewModelScope.launch {
            eventsChannel.send(
                when (failure) {
                    is Failure.ApiError -> {
                        WeatherEvent.ShowToast(resourcesProvider.getString(R.string.location_not_found))
                    }

                    Failure.NetworkError -> {
                        WeatherEvent.ShowToast(resourcesProvider.getString(R.string.check_your_internet_connection))
                    }

                    Failure.UnknownError -> {
                        WeatherEvent.ShowToast(resourcesProvider.getString(R.string.error_general))
                    }
                }
            )
        }
        uiState.update { it.copy(isLoading = false) }
    }
}
