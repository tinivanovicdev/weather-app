package com.ivanovictin.weatherapp.features.weather.domain.mapper

import com.ivanovictin.weatherapp.features.weather.domain.model.Forecast
import com.ivanovictin.weatherapp.features.weather.ui.model.UiDayForecast
import com.ivanovictin.weatherapp.features.weather.ui.model.UiForecast
import javax.inject.Inject

class ForecastToUiForecastMapper @Inject constructor(
    private val astroForecastToUiAstroForcastMapper: AstroForecastToUiAstroForcastMapper,
    private val dayForecastToUiDayForecastMapper: DayForecastToUiDayForecastMapper,
    private val hourForecastToUiHourForecastMapper: HourForecastToUiHourForecastMapper,
) {
    fun map(origin: Forecast): UiForecast {
        return with(origin) {
            UiForecast(
                astro = astroForecastToUiAstroForcastMapper.map(astro),
                date = date,
                dateEpoch = dateEpoch,
                day = dayForecastToUiDayForecastMapper.map(day),
                hour = hour.map { hourForecastToUiHourForecastMapper.map(it) }
            )
        }
    }
}