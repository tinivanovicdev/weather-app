package com.ivanovictin.weatherapp.common.network

import android.content.Context
import com.ivanovictin.weatherapp.BuildConfig
import com.ivanovictin.weatherapp.R
import com.ivanovictin.weatherapp.common.di.KEY_IS_DEBUG_BUILD
import com.ivanovictin.weatherapp.common.di.KEY_WEATHER_API_KEY
import com.ivanovictin.weatherapp.common.di.KEY_WEATHER_API_RETROFIT_INSTANCE
import com.ivanovictin.weatherapp.common.di.KEY_WEATHER_API_URL
import com.ivanovictin.weatherapp.common.network.call.CoroutineEitherCallAdapterFactory
import com.ivanovictin.weatherapp.common.network.interceptor.WeatherApiAuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CACHE_MAX_SIZE = 20 * 1024L * 1024L
    private const val TIMEOUT_DURATION = 180L

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Named(KEY_WEATHER_API_URL)
    fun provideWeatherApiUrl(@ApplicationContext context: Context): String {
        return context.getString(R.string.weather_api_base_url)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named(KEY_IS_DEBUG_BUILD) isDebugBuild: Boolean,
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        weatherApiAuthInterceptor: WeatherApiAuthInterceptor,
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            if (isDebugBuild) {
                interceptors() += httpLoggingInterceptor
            }
            interceptors() += weatherApiAuthInterceptor
            cache(
                Cache(
                    File(context.cacheDir, "okhttp_cache"),
                    CACHE_MAX_SIZE
                )
            )
            connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            build()
        }

    @Provides
    @Named(KEY_WEATHER_API_KEY)
    fun provideWeatherApiKey(): String {
        return BuildConfig.WEATHER_API_KEY
    }

    @Provides
    fun provideConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        @Named(KEY_WEATHER_API_KEY) weatherApiKey: String,
    ): WeatherApiAuthInterceptor {
        return WeatherApiAuthInterceptor(weatherApiKey)
    }

    @Named(KEY_WEATHER_API_RETROFIT_INSTANCE)
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        @Named(KEY_WEATHER_API_URL) baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineEitherCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}
