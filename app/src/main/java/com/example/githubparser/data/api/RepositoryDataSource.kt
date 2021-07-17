package com.example.githubparser.data.api

import javax.inject.Inject

class RepositoryDataSource @Inject constructor(
    private val repositoryApi: RepositoryApi
) : BaseDataSource() {
    suspend fun getRepositories() = getResult { repositoryApi.getRepositories() }
}