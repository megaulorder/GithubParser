package com.example.githubparser.data.api

import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.data.model.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryApi {

    @GET("search/repositories?q=user:megaulorder&sort=updated&order=desc")
    suspend fun getAllRepositories(): Response<RepositoriesList>

    @GET("repos/{login}/{name}")
    suspend fun getRepository(
        @Path("login", encoded = true) login: String,
        @Path("name", encoded = true) repositoryName: String,
    ): Response<Repository>
}