package com.example.githubparser.data.repository

import com.example.githubparser.data.api.RepositoryDataSource
import com.example.githubparser.utils.getData
import javax.inject.Inject

class RepositoryRepository @Inject constructor(
    private val repositoryDataSource: RepositoryDataSource
) {
    fun getRepositories(query: String) = getData { repositoryDataSource.getRepositories(query) }
    fun getRepository(login: String, repositoryName: String) =
        getData { repositoryDataSource.getRepository(login, repositoryName) }
}