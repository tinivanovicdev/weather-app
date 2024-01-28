package com.ivanovictin.weatherapp.domain

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.Failure
import com.ivanovictin.weatherapp.features.home.domain.WeatherRepository
import com.ivanovictin.weatherapp.features.home.domain.mapper.BooleanToShowAirQualityStringMapper
import com.ivanovictin.weatherapp.features.home.domain.model.Weather
import com.ivanovictin.weatherapp.features.home.domain.usecase.GetCurrentWeatherUseCase
import junit.framework.TestCase.assertEquals
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

@RunWith(MockitoJUnitRunner::class)
class GetCurrentWeatherUseCaseTest {

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var booleanToShowAirQualityStringMapper: BooleanToShowAirQualityStringMapper

    private lateinit var sut: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        sut = GetCurrentWeatherUseCase(weatherRepository, booleanToShowAirQualityStringMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke with valid data should return weather data`() = runTest {
        // given
        val locationName = "TestLocation"
        val showAirQualityData = false
        val expectedWeather = createFakeWeather()

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            whenever(weatherRepository.getWeather(locationName, "mappedString")).thenReturn(
                Either.Right(expectedWeather)
            )
            whenever(booleanToShowAirQualityStringMapper.map(showAirQualityData))
                .thenReturn("mappedString")
        }

        // when
        val result = sut.invoke(locationName, showAirQualityData)
        collectJob.cancel()

        // then
        assertEquals(Either.Right(expectedWeather), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke with invalid data should return failure`() = runTest {
        // given
        val locationName = "InvalidLocation"
        val showAirQualityData = false
        val expectedResult = Either.Left(Failure.ApiError(400))

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            whenever(weatherRepository.getWeather(locationName, "mappedString")).thenReturn(
                expectedResult
            )
            whenever(booleanToShowAirQualityStringMapper.map(showAirQualityData))
                .thenReturn("mappedString")
        }

        // when
        val result = sut.invoke(locationName, showAirQualityData)
        collectJob.cancel()

        // then
        assertEquals(expectedResult, result)
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
}
