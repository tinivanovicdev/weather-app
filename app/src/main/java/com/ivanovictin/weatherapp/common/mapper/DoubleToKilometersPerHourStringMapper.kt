package com.ivanovictin.weatherapp.common.mapper

import javax.inject.Inject

class DoubleToKilometersPerHourStringMapper @Inject constructor() {
    fun map(origin: Double?): String {
        return "${origin?.toInt()} $KIlOMETRES_PER_HOUR_SIGN"
    }

    private companion object {
        const val KIlOMETRES_PER_HOUR_SIGN = "Km/h"
    }
}
