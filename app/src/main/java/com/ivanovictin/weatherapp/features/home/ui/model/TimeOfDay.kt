package com.ivanovictin.weatherapp.features.home.ui.model

import androidx.annotation.DrawableRes
import com.ivanovictin.weatherapp.R

enum class TimeOfDay(val title: String, @DrawableRes val icon: Int) {
    DAY("Day", R.drawable.ic_sunny),
    NIGHT("Night", R.drawable.ic_moon),
}
