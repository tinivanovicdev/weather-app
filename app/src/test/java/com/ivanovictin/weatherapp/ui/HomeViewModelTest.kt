package com.ivanovictin.weatherapp.ui

import com.google.common.truth.Truth.assertThat
import com.ivanovictin.weatherapp.common.di.ResourcesProvider
import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.features.home.domain.mapper.WeatherToUiWeatherMapper
import com.ivanovictin.weatherapp.features.home.domain.model.Weather
import com.ivanovictin.weatherapp.features.home.domain.usecase.GetCurrentWeatherUseCase
import com.ivanovictin.weatherapp.features.home.ui.HomeAction
import com.ivanovictin.weatherapp.features.home.ui.HomeViewModel
import com.ivanovictin.weatherapp.features.home.ui.model.TimeOfDay
import com.ivanovictin.weatherapp.features.home.ui.model.UiWeather
import com.ivanovictin.weatherapp.features.home.ui.model.WeatherImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @Mock
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Mock
    private lateinit var weatherToUiWeatherMapper: WeatherToUiWeatherMapper

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider

    private lateinit var sut: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        sut = HomeViewModel(getCurrentWeatherUseCase, weatherToUiWeatherMapper, resourcesProvider)
    }

    @Test
    fun `initially should emit state with is loading set to false`() {
        val uiState = sut.uiState.value
        assertThat(uiState.isLoading).isFalse()
    }

    @Test
    fun `onAction with QuerySubmitted and successful weather retrieval should update uiState`() =
        runTest {
            //given
            val fakeWeather = createFakeWeather()

            //when
            val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                whenever(
                    getCurrentWeatherUseCase.invoke(
                        locationName = "fake location",
                        showAirQualityData = false
                    )
                ).thenReturn(Either.Right(fakeWeather))

                whenever(weatherToUiWeatherMapper.map(fakeWeather)).thenReturn(createFakeUiWeather())
            }

            collectJob.cancel()

            //then
            assertThat(sut.uiState.value.uiWeather != null)
        }

    @Test
    fun `onAction with QueryChanged should update uiState query property`() =
        runTest {
            //when
            sut.onAction(HomeAction.QueryChanged("fake location"))

            //then
            assertThat(sut.uiState.value.query == "fake location")
        }

    private fun createFakeWeather(): Weather {
        return Weather(
            country = null,
            location = null,
            temperatureInCelsius = null,
            isDay = false,
            condition = null,
            pressureInMillibars = null,
            windInKilometresPerHour = null,
            cloudPercentage = null,
            windDirection = null,
            humidity = "",
            weatherImageCode = null
        )
    }

    private fun createFakeUiWeather(): UiWeather {
        return UiWeather(
            country = "",
            location = "",
            condition = "",
            currentTemperatureInCelsius = "",
            pressure = "",
            windSpeedInKmh = "",
            humidity = "",
            windDirection = "",
            timeOfDay = TimeOfDay.DAY,
            weatherImage = WeatherImage.SUNNY,
        )
    }
}
