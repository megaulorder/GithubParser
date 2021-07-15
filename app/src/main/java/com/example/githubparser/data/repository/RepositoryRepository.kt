package com.example.githubparser.data.repository

import com.example.githubparser.data.api.RepositoryApi
import com.example.githubparser.data.model.RepositoriesList
import retrofit2.Call
import javax.inject.Inject

class RepositoryRepository @Inject constructor(
    private val repositoryApi: RepositoryApi
) {
    fun parseJson(): Call<RepositoriesList> {
        return repositoryApi.getRepositories()
    }
}