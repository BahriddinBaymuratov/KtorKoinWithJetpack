package com.example.ktorkoin.network

sealed class Response<out T> {
    data class Error(val text: String) : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
}