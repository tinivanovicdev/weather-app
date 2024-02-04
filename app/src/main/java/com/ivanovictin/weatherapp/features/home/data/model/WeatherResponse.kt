package com.ivanovictin.weatherapp.features.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("location") var location: LocationResponse,
    @SerialName("forecast") val forecastResponse: ForecastResponse,
    @SerialName("current") var currentWeather: CurrentWeatherResponse,
)

@Serializable
data class LocationResponse(
    @SerialName("name") var name: String?,
    @SerialName("region") var region: String?,
    @SerialName("country") var country: String?,
    @SerialName("lat") var latitude: Double?,
    @SerialName("lon") var longitude: Double?,
    @SerialName("tz_id") var timeZoneIdentifier: String?,
    @SerialName("localtime_epoch") var localtimeEpoch: Int?,
    @SerialName("localtime") var localtime: String?,
)

@Serializable
data class WeatherConditionResponse(
    @SerialName("text") var status: String?,
    @SerialName("icon") var iconUrl: String?,
    @SerialName("code") var code: Int?,
)

@Serializable
data class CurrentWeatherResponse(
    @SerialName("last_updated_epoch") var lastUpdatedEpoch: Int?,
    @SerialName("last_updated") var lastUpdated: String?,
    @SerialName("temp_c") var temperatureInCelsius: Double?,
    @SerialName("temp_f") var temperatureInFahrenheit: Double?,
    @SerialName("is_day") var isDay: Int?,
    @SerialName("condition") var weatherCondition: WeatherConditionResponse?,
    @SerialName("wind_mph") var windSpeedInMilesPerHour: Double?,
    @SerialName("wind_kph") var windSpeedInKilometersPerHour: Double?,
    @SerialName("wind_degree") var windDegree: Int?,
    @SerialName("wind_dir") var windDirection: String?,
    @SerialName("pressure_mb") var pressureInMillibars: Double?,
    @SerialName("pressure_in") var pressureInInches: Double?,
    @SerialName("precip_mm") var precipitationAmountInMillimeters: Double?,
    @SerialName("precip_in") var precipitationAmountInInches: Double?,
    @SerialName("humidity") var humidityPercentage: Int?,
    @SerialName("cloud") var cloudPercentage: Int?,
    @SerialName("feelslike_c") var feelsLikeTemperatureInCelsius: Double?,
    @SerialName("feelslike_f") var feelsLikeTemperatureInFahrenheit: Double?,
    @SerialName("vis_km") var visibilityInKilometres: Double?,
    @SerialName("vis_miles") var visibilityInMiles: Double?,
    @SerialName("uv") var ultraVioletIndex: Double?,
    @SerialName("gust_mph") var windGustInMilesPerHour: Double?,
    @SerialName("gust_kph") var windGustInKilometresPerHour: Double?,
)
