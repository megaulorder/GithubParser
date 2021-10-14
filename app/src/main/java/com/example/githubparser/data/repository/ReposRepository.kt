package com.example.githubparser.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubparser.data.db.ReposDatabase
import com.example.githubparser.data.model.Repo
import com.example.githubparser.data.service.GithubApi
import kotlinx.coroutines.flow.Flow

class ReposRepository(
	private val service: GithubApi,
	private val database: ReposDatabase
) {

	fun search(query: String): Flow<PagingData<Repo>> {
		Log.d("GithubRepository", "ReposRepository: New query: $query")

		val databaseQuery = "%${query.replace(' ', '%')}%"

		@OptIn(ExperimentalPagingApi::class)
		return Pager(
			config = PagingConfig(pageSize = 30, enablePlaceholders = false),
			remoteMediator = ReposRemoteMediator(query, service, database),
			pagingSourceFactory = { database.reposDao().reposByName(databaseQuery) }
		).flow
	}
}
