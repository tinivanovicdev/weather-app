package com.ivanovictin.weatherapp.common.mapper

import javax.inject.Inject

class StringToPercentageStringMapper @Inject constructor() {
    fun map(origin: String): String {
        return "$origin$PERCENTAGE_SIGN"
    }

    private companion object {
        const val PERCENTAGE_SIGN = "%"
    }
}
