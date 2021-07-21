package com.example.githubparser.data.repository

import com.example.githubparser.data.api.RepositoryDataSource
import com.example.githubparser.utils.getData
import javax.inject.Inject

class RepositoryRepository @Inject constructor(
    private val repositoryDataSource: RepositoryDataSource
) {
    fun getRepositories() = getData { repositoryDataSource.getRepositories() }
    fun getRepository(name: String) =
        getData { repositoryDataSource.getRepository(name) }
}