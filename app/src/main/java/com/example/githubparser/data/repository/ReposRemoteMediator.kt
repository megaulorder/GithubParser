package com.example.githubparser.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.githubparser.data.db.PageKeys
import com.example.githubparser.data.db.ReposDatabase
import com.example.githubparser.data.model.Repo
import com.example.githubparser.data.service.GithubApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ReposRemoteMediator(
	private val query: String,
	private val service: GithubApi,
	private val reposDatabase: ReposDatabase
) : RemoteMediator<Int, Repo>() {

	override suspend fun load(loadType: LoadType, state: PagingState<Int, Repo>): MediatorResult {
		val page = when (loadType) {
			LoadType.REFRESH -> 1
			LoadType.PREPEND -> {
				val remoteKeys = getRemoteKeyForFirstItem(state)
				val prevKey = remoteKeys?.prevKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
				prevKey
			}
			LoadType.APPEND -> {
				val remoteKeys = getRemoteKeyForLastItem(state)
				val nextKey = remoteKeys?.nextKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
				nextKey
			}
		}

		try {
			val apiResponse = service.searchRepos(query, page, state.config.pageSize)

			val repos = apiResponse.items
			val endOfPaginationReached = repos.isEmpty()
			reposDatabase.withTransaction {
				if (loadType == LoadType.REFRESH) {
					reposDatabase.pageKeysDao().clearPageKeys()
					reposDatabase.reposDao().clearRepos()
				}
				val prevKey = if (page == 1) null else page - 1
				val nextKey = if (endOfPaginationReached) null else page + 1
				val keys = repos.map {
					PageKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
				}
				reposDatabase.pageKeysDao().insertAll(keys)
				reposDatabase.reposDao().insertAll(repos)
			}
			return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (exception: IOException) {
			return MediatorResult.Error(exception)
		} catch (exception: HttpException) {
			return MediatorResult.Error(exception)
		}
	}

	private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Repo>): PageKeys? {
		return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
			reposDatabase.pageKeysDao().pageKeysByRepoId(repo.id)
		}
	}

	private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Repo>): PageKeys? {
		return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { repo ->
			reposDatabase.pageKeysDao().pageKeysByRepoId(repo.id)
		}
	}
}
