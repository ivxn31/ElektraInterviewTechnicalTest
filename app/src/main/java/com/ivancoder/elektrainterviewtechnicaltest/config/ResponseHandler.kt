package com.ivancoder.elektrainterviewtechnicaltest.config

import timber.log.Timber
import java.lang.Exception

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T?): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleError(msg: String?,data: T?): Resource<T> {
        return Resource.error(msg,data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        Timber.e("handleException ${e.message}")
        return Resource.error(e.message,null)
    }
}