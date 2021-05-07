package com.spesolution.myapplication.core.data.datasource.remote

import com.spesolution.myapplication.core.data.model.Results
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Ian Damping on 07,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
class BaseSourceImpl : BaseSource {
    override suspend fun <T> oneShotCalls(call: suspend () -> Response<T>): Results<T> {
        try {
            val response = call.invoke()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Results.Success(body)
                } else {
                    Results.Error(Exception("body is null"))
                }
            } else return Results.Error(Exception("response not success"))
        } catch (e: Exception) {
            return when (e) {
                is IOException -> {
                    Results.Error(e)
                }
                is SocketTimeoutException -> {
                    Results.Error(e)
                }
                else -> {
                    Results.Error(e)
                }
            }
        }
    }
}