package com.ivanovictin.weatherapp.features.weather.domain.usecase

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.features.weather.domain.WeatherRepository
import com.ivanovictin.weatherapp.features.weather.domain.mapper.BooleanToShowAirQualityStringMapper
import com.ivanovictin.weatherapp.features.weather.domain.model.Weather
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val booleanToShowAirQualityStringMapper: BooleanToShowAirQualityStringMapper,
) : suspend (String, Int, Boolean) -> EitherResult<Weather> {
    override suspend fun invoke(
        locationName: String,
        numberOfDays: Int,
        showAirQualityData: Boolean,
    ): EitherResult<Weather> {
        return weatherRepository.getWeatherWithForecast(
            location = locationName,
            numberOfDays = numberOfDays,
            getAirQuality = booleanToShowAirQualityStringMapper.map(showAirQualityData),
        )
    }
}
