package com.spesolution.myapplication.core.domain.model

sealed class DomainResult<out T> {
    data class Data<out T>(val data: T) : DomainResult<T>()
    data class Error(val message: String) : DomainResult<Nothing>()
    object Loading : DomainResult<Nothing>()
}