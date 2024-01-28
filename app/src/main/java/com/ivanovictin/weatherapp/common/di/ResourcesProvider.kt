package com.ivanovictin.weatherapp.common.di

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {
    fun getString(@StringRes stringResId: Int) = appContext.getString(stringResId)
}
