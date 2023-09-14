package com.cmesquita.realstation.data.model

sealed class Result<out T> {

    data class Success<out T>(val value: T) : Result<T>()

    data class Error(val code: Int, val message: String?) : Result<Nothing>()
}
