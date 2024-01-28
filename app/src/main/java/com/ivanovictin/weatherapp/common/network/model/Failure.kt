package com.ivanovictin.weatherapp.common.network.model

sealed interface Failure {
    object NetworkError : Failure
    object UnknownError : Failure
    data class ApiError(val statusCode: Int) : Failure
}
