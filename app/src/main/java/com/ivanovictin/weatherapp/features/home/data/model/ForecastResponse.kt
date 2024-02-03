package com.ivanovictin.weatherapp.features.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    @SerialName("text") val forecastDayResponse: List<ForecastDayResponse>
)

@Serializable
data class ForecastDayResponse(
    @SerialName("astro") val astro: AstroResponse,
    @SerialName("date") val date: String,
    @SerialName("date_epoch") val dateEpoch: Int,
    @SerialName("day") val day: DayForecastResponse,
    @SerialName("hour") val hour: List<HourResponse>
)

@Serializable
data class AstroResponse(
    @SerialName("is_moon_up") val isMoonUp: Int,
    @SerialName("is_sun_up") val isSunUp: Int,
    @SerialName("moon_illumination") val moonIllumination: Int,
    @SerialName("moon_phase") val moonPhase: String,
    @SerialName("moonrise") val moonriseTime: String,
    @SerialName("moonset") val moonsetTime: String,
    @SerialName("sunrise") val sunriseTime: String,
    @SerialName("sunset") val sunsetTime: String
)

@Serializable
data class DayForecastResponse(
    @SerialName("avghumidity") val avghumidity: Int,
    @SerialName("avgtemp_c") val averageTempInCelsius: Double,
    @SerialName("avgtemp_f") val averageTempInFahrenheit: Double,
    @SerialName("avgvis_km") val averageVisibilityKilometres: Double,
    @SerialName("avgvis_miles") val averageVisibilityMiles: Double,
    @SerialName("condition") val condition: WeatherConditionResponse,
    @SerialName("daily_chance_of_rain") val dailyChangeOfRainPercentage: Int,
    @SerialName("daily_chance_of_snow") val dailyChangeOfSnowPercentage: Int,
    @SerialName("daily_will_it_rain") val dailyWillItRainPercentage: Int,
    @SerialName("daily_will_it_snow") val dailyWillItSnowPercentage: Int,
    @SerialName("maxtemp_c") val maxTempInCelsius: Double,
    @SerialName("maxtemp_f") val maxTempInFahrenheit: Double,
    @SerialName("maxwind_kph") val maxWindInKilometresPerHour: Double,
    @SerialName("maxwind_mph") val maxWindInMilesPerHour: Double,
    @SerialName("mintemp_c") val minimalTempInCelsius: Double,
    @SerialName("mintemp_f") val minimalTempInFahrenheit: Double,
    @SerialName("totalprecip_in") val totalPrecipitationInInches: Double,
    @SerialName("totalprecip_mm") val totalPrecipitationInMillimetres: Double,
    @SerialName("totalsnow_cm") val totalSnowInCentimetres: Double,
    @SerialName("uv") val uvIndex: Double
)

@Serializable
data class HourResponse(
    @SerialName("chance_of_rain") val chanceOfRainPercentage: Int,
    @SerialName("chance_of_snow") val chanceOfSnowPercentage: Int,
    @SerialName("cloud") val cloud: Int,
    @SerialName("condition") val condition: WeatherConditionResponse,
    @SerialName("dewpoint_c") val dewPointInCelsius: Double,
    @SerialName("dewpoint_f") val dewPointInFahrenheit: Double,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeInFahrenheit: Double,
    @SerialName("gust_kph") val gustInKilometresPerHour: Double,
    @SerialName("gust_mph") val gustInMilesPerHour: Double,
    @SerialName("heatindex_c") val heatIndexInCelsius: Double,
    @SerialName("heatindex_f") val heatIndexInFahrenheit: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("is_day") val isDay: Int,
    @SerialName("precip_in") val precipitationInInches: Double,
    @SerialName("precip_mm") val precipitationInMillimetres: Double,
    @SerialName("pressure_in") val pressureInInches: Double,
    @SerialName("pressure_mb") val pressureInMillibars: Double,
    @SerialName("snow_cm") val snowInCentimetres: Double,
    @SerialName("temp_c") val temperatureInCelsius: Double,
    @SerialName("temp_f") val temperatureInFahrenheit: Double,
    @SerialName("time") val time: String,
    @SerialName("time_epoch") val timeEpoch: Int,
    @SerialName("uv") val uvIndex: Double,
    @SerialName("vis_km") val visibilityInKilometres: Double,
    @SerialName("vis_miles") val visibilityInMiles: Double,
    @SerialName("will_it_rain") val willItRain: Int,
    @SerialName("will_it_snow") val willItSnow: Int,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("wind_kph") val windInKilometresPerHour: Double,
    @SerialName("wind_mph") val windInMilesPerHour: Double,
    @SerialName("windchill_c") val windChillInCelsius: Double,
    @SerialName("windchill_f") val windChillInFahrenheit: Double
)