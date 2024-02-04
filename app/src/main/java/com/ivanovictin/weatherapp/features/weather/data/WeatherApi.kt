package com.ivanovictin.weatherapp.features.weather.data

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.features.weather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    suspend fun getWeatherWithForecast(
        @Query("q") locationName: String,
        @Query("days") days: Int,
        @Query("aqi") airQualityDataFlag: String?,
    ): EitherResult<WeatherResponse>
}
