package com.ivanovictin.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application() {

    @Inject
    lateinit var tree: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        Timber.plant(tree)
    }
}
