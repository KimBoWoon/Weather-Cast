package com.bowoon.android.data.util

sealed class Status {
    object Loading : Status()
    data class Success<T>(val data: T) : Status()
    data class Failure(val message: String) : Status()
}