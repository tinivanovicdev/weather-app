package com.ivanovictin.weatherapp.features.weather.data.mapper

import javax.inject.Inject

class IntToBooleanMapper @Inject constructor() {
    fun map(isDay: Int?): Boolean {
        return when (isDay) {
            0 -> false
            else -> true
        }
    }
}
