package com.ivanovictin.weatherapp.features.weather.data.mapper

import com.ivanovictin.weatherapp.features.weather.data.model.AstroResponse
import com.ivanovictin.weatherapp.features.weather.domain.model.AstroForecast
import javax.inject.Inject

class AstroResponseToAstroForecastMapper @Inject constructor(
    private val intToBooleanMapper: IntToBooleanMapper,
) {
    fun map(origin: AstroResponse): AstroForecast {
        return with(origin) {
            AstroForecast(
                isMoonUp = intToBooleanMapper.map(isMoonUp),
                isSunUp = intToBooleanMapper.map(isSunUp),
                moonIllumination = moonIllumination,
                moonPhase = moonPhase,
                moonriseTime = moonriseTime,
                moonsetTime = moonsetTime,
                sunriseTime = sunriseTime,
                sunsetTime = sunsetTime
            )
        }
    }
}