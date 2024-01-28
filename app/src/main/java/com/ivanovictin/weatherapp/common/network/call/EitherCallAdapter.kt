package com.ivanovictin.weatherapp.common.network.call

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.Failure
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<Either<Failure, T>>> {

    override fun responseType() = successType

    override fun adapt(call: Call<T>): Call<Either<Failure, T>> =
        EitherCall(call, successType)
}
