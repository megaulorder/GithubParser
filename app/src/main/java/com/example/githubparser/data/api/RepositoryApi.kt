package com.example.githubparser.data.api

import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.data.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryApi {

    @GET("search/repositories")
    suspend fun getAllRepositories(
        @Query("q", encoded = true) query: String
    ): Response<RepositoriesList>

    @GET("repos/{login}/{name}")
    suspend fun getRepository(
        @Path("login", encoded = true) login: String,
        @Path("name", encoded = true) repositoryName: String,
    ): Response<Repository>
}