package com.ivanovictin.weatherapp.common.mapper

import javax.inject.Inject

class DoubleToCelsiusStringMapper @Inject constructor() {
    fun map(origin: Double?): String {
        return "${origin?.toInt()}$CELSIUS_SIGN"
    }

    private companion object {
        const val CELSIUS_SIGN = "°C"
    }
}
