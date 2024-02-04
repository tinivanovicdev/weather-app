package com.ivanovictin.weatherapp.features.weather.domain.mapper

import com.ivanovictin.weatherapp.features.weather.domain.model.AstroForecast
import com.ivanovictin.weatherapp.features.weather.ui.model.UiAstroForecast
import javax.inject.Inject

class AstroForecastToUiAstroForcastMapper @Inject constructor(
    private val isDayBooleanToTimeOfDayMapper: IsDayBooleanToTimeOfDayMapper,
) {
    fun map(origin: AstroForecast): UiAstroForecast {
        return with(origin) {
            UiAstroForecast(
                timeOfDay = isDayBooleanToTimeOfDayMapper.map(isSunUp),
                moonIlluminationPercentage = moonIllumination,
                moonPhase = moonPhase,
                moonriseTime = moonriseTime,
                moonsetTime = moonsetTime,
                sunriseTime = sunriseTime,
                sunsetTime = sunsetTime,
            )
        }
    }
}