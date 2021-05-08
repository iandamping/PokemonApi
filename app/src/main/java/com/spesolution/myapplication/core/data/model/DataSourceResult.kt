package com.spesolution.myapplication.core.data.model

sealed class DataSourceResult<out T> {
    data class SourceValue<out T>(val data: T) : DataSourceResult<T>()
    data class SourceError(val exception: Exception) : DataSourceResult<Nothing>()
}