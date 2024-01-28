package com.ivanovictin.weatherapp.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class WeatherApiAuthInterceptor @Inject constructor(
    private val weatherApiKeyValue: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(WEATHER_API_HEADER, weatherApiKeyValue)
            .build()
        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }

    private companion object {
        const val WEATHER_API_HEADER = "key"
    }
}
