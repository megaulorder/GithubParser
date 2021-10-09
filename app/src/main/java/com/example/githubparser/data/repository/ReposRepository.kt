package com.example.githubparser.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubparser.data.model.Repo
import com.example.githubparser.data.service.GithubApi
import kotlinx.coroutines.flow.Flow

class ReposRepository(private val service: GithubApi) {

	fun search(query: String): Flow<PagingData<Repo>> {
		Log.d("GithubRepository", "ReposRepository: New query: $query")

		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
			pagingSourceFactory = { GithubPagingSource(service, query) }
		).flow
	}
}
