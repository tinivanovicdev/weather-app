package com.ivanovictin.weatherapp.features.home.data.mapper

import javax.inject.Inject

class IsDayIntMapperToBooleanMapper @Inject constructor() {
    fun map(isDay: Int?): Boolean {
        return when (isDay) {
            0 -> false
            else -> true
        }
    }
}
