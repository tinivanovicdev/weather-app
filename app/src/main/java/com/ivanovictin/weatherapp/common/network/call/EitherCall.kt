package com.ivanovictin.weatherapp.common.network.call

import com.ivanovictin.weatherapp.common.network.model.Either
import com.ivanovictin.weatherapp.common.network.model.Failure
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException

class EitherCall<T>(
    private val proxy: Call<T>,
    private val successType: Type,
) : Call<Either<Failure, T>> {

    @Suppress("TooGenericExceptionCaught")
    override fun enqueue(callback: Callback<Either<Failure, T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = try {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    when {
                        response.isSuccessful && body != null -> Either.Right(body)
                        response.isSuccessful && successType == Unit::class.java ->
                            Either.Right(Unit) as Either<Failure, T>
                        errorBody != null -> Either.Left(
                            Failure.ApiError(response.code())
                        )
                        else -> {
                            Timber.e(response.message())
                            Either.Left(Failure.UnknownError)
                        }
                    }
                } catch (exception: Throwable) {
                    when (exception) {
                        is ConnectException -> Either.Left(Failure.NetworkError)
                        else -> {
                            Timber.e(exception)
                            Either.Left(Failure.UnknownError)
                        }
                    }
                }
                callback.onResponse(this@EitherCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val error = when (t) {
                    is IOException -> Either.Left(Failure.NetworkError)
                    else -> {
                        Timber.e(t)
                        Either.Left(Failure.UnknownError)
                    }
                }
                callback.onResponse(this@EitherCall, Response.success(error))
            }
        })
    }

    override fun clone(): Call<Either<Failure, T>> = EitherCall(proxy.clone(), successType)

    override fun execute(): Response<Either<Failure, T>> = throw NotImplementedError()

    override fun isExecuted() = proxy.isExecuted

    override fun cancel() = proxy.cancel()

    override fun isCanceled() = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()
}
