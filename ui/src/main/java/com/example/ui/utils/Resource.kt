package com.example.ui.utils

sealed class Resource<out T> {
    object Empty : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Success<T>(val value: T) : Resource<T>()
    data class Failure(val message: String) : Resource<Nothing>()
}
