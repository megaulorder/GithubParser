package com.example.githubparser.data.api

import com.example.githubparser.data.model.RepositoriesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryApi {

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Long,
        @Query("per_page") perPage: Int
    ): Single<RepositoriesList>
}