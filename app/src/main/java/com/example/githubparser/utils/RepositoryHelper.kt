package com.example.githubparser.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> getData(apiCall: suspend () -> Resource<T>): LiveData<Resource<T?>> =
    liveData(
        Dispatchers.IO
    ) {
        emit(Resource.loading())

        val responseStatus = apiCall.invoke()

        if (responseStatus.status == Resource.Status.SUCCESS) {
            emit(Resource.success(responseStatus.data))

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }
