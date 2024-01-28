package com.ivanovictin.weatherapp.features.home.domain.mapper

import javax.inject.Inject

class BooleanToShowAirQualityStringMapper @Inject constructor() {
    fun map(origin: Boolean): String {
        return when (origin) {
            true -> SHOW_AIR_QUALITY_VALUE
            false -> DO_NOT_SHOW_AIR_QUALITY_VALUE
        }
    }

    private companion object {
        const val SHOW_AIR_QUALITY_VALUE = "yes"
        const val DO_NOT_SHOW_AIR_QUALITY_VALUE = "no"
    }
}
