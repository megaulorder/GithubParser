package com.example.githubparser.data.repository

import com.example.githubparser.data.api.ApiBuilder
import com.example.githubparser.data.api.RepositoryApi
import com.example.githubparser.data.model.RepositoriesList
import retrofit2.Call

class RepositoryRepository {
    fun parseJson(): Call<RepositoriesList> {
        return ApiBuilder.build(RepositoryApi::class.java).getRepositories()
    }
}