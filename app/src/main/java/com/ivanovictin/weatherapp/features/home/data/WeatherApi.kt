package com.ivanovictin.weatherapp.features.home.data

import com.ivanovictin.weatherapp.common.network.model.EitherResult
import com.ivanovictin.weatherapp.features.home.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("q") locationName: String,
        @Query("aqi") airQualityDataFlag: String?,
    ): EitherResult<WeatherResponse>
}
