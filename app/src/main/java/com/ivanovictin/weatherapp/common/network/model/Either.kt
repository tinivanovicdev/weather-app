package com.ivanovictin.weatherapp.common.network.model

import kotlinx.coroutines.Deferred

sealed class Either<out L, out R> {

    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun left(): L = (this as Left<L>).value
    fun right(): R = (this as Right<R>).value

    @Suppress("MemberNameEqualsClassName")
    inline fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(value)
            is Right -> fnR(value)
        }
}

inline fun <L, R, NR> Either<L, R>.flatMap(transform: (R) -> Either<L, NR>): Either<L, NR> =
    when (this) {
        is Either.Left<L> -> this
        else -> transform(right())
    }

inline fun <L, R, NR> Either<L, R>.map(transform: (R) -> NR): Either<L, NR> =
    when (this) {
        is Either.Left<L> -> this
        else -> {
            val newRight = transform(right())
            Either.Right(newRight)
        }
    }

suspend fun <T, R> zip(
    deferred0: Deferred<Either<Failure, T>>,
    deferred1: Deferred<Either<Failure, R>>,
): Either<Failure, Pair<T, R>> {
    val response0 = deferred0.await()
    val response1 = deferred1.await()
    val errors = listOf(response0, response1).filter { it.isLeft }
    return if (errors.isNotEmpty()) {
        Either.Left(errors.first().left())
    } else {
        Either.Right(Pair(response0.right(), response1.right()))
    }
}

typealias EitherResult<T> = Either<Failure, T>
