package com.ivanovictin.weatherapp

import android.content.Context
import android.content.SharedPreferences
import com.ivanovictin.weatherapp.common.di.KEY_IS_DEBUG_BUILD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named(KEY_IS_DEBUG_BUILD)
    fun provideIsDebugBuild() = BuildConfig.DEBUG

    @Provides
    fun provideJson(): Json = Json {
        coerceInputValues = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideTimberTree(
        @Named(KEY_IS_DEBUG_BUILD) isDebug: Boolean,
    ): Timber.Tree {
        return if (isDebug) {
            Timber.DebugTree()
        } else {
            Timber.asTree()
        }
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "${context.packageName}_preferences",
            Context.MODE_PRIVATE
        )
    }
}
