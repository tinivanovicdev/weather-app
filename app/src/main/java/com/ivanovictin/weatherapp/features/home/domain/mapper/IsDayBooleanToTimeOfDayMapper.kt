package com.ivanovictin.weatherapp.features.home.domain.mapper

import com.ivanovictin.weatherapp.features.home.ui.model.TimeOfDay
import javax.inject.Inject

class IsDayBooleanToTimeOfDayMapper @Inject constructor() {
    fun map(origin: Boolean): TimeOfDay {
        return when (origin) {
            true -> TimeOfDay.DAY
            false -> TimeOfDay.NIGHT
        }
    }
}
