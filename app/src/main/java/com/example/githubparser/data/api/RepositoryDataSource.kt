package com.example.githubparser.data.api

import javax.inject.Inject

class RepositoryDataSource @Inject constructor(
    private val repositoryApi: RepositoryApi
) : BaseDataSource() {
    suspend fun getRepositories(query: String) =
        getResult { repositoryApi.getAllRepositories(query) }

    suspend fun getRepository(login: String, repositoryName: String) =
        getResult { repositoryApi.getRepository(login, repositoryName) }
}