package com.ivanovictin.weatherapp.features.home.data.mapper

import com.ivanovictin.weatherapp.features.home.data.model.ForecastDayResponse
import com.ivanovictin.weatherapp.features.home.domain.model.Forecast
import javax.inject.Inject

class ForecastDayResponseToForecastMapper @Inject constructor(
    private val astroResponseToAstroForecastMapper: AstroResponseToAstroForecastMapper,
    private val dayForecastResponseToDayForecastMapper: DayForecastResponseToDayForecastMapper,
    private val hourForecastResponseToHourForecastMapper: HourForecastResponseToHourForecastMapper,
) {
    fun map(origin: ForecastDayResponse): Forecast {
        return with(origin) {
            Forecast(
                astro = astroResponseToAstroForecastMapper.map(astro),
                date = origin.date,
                dateEpoch = origin.dateEpoch,
                day = dayForecastResponseToDayForecastMapper.map(day),
                hour = hour.map { hourForecastResponseToHourForecastMapper.map(it) }
            )
        }
    }
}