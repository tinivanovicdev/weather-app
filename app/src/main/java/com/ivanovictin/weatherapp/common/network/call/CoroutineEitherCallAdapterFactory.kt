package com.ivanovictin.weatherapp.common.network.call

import com.ivanovictin.weatherapp.common.network.model.Either
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CoroutineEitherCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                Either::class.java -> {
                    val resultType = getParameterUpperBound(1, callType as ParameterizedType)
                    EitherCallAdapter<Any>(resultType)
                }
                else -> null
            }
        }
        else -> null
    }

    companion object {
        fun create() = CoroutineEitherCallAdapterFactory()
    }
}
