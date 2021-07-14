package com.example.githubparser.data.api

import com.example.githubparser.utils.constants.UrlConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {

    fun getLogging(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor
    }

    private val client = OkHttpClient.Builder().addInterceptor(getLogging()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(UrlConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }
}
