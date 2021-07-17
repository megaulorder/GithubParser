package com.example.githubparser.data.api

import com.example.githubparser.data.model.RepositoriesList
import retrofit2.Response
import retrofit2.http.GET

interface RepositoryApi {

    @GET("search/repositories?q=user:megaulorder&sort=updated&order=desc")
    suspend fun getRepositories(): Response<RepositoriesList>
}