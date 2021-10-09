package com.example.githubparser.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubparser.data.model.Repo
import com.example.githubparser.data.service.GithubApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
const val ITEMS_PER_PAGE = 30

class GithubPagingSource(
	private val service: GithubApi,
	private val query: String
) : PagingSource<Int, Repo>() {

	override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
		val position = params.key ?: STARTING_PAGE_INDEX

		return try {
			val response = service.searchRepos(query, position, params.loadSize)
			val repos = response.items

			val nextKey = if (repos.isEmpty()) {
				null
			} else {
				position + (params.loadSize / ITEMS_PER_PAGE)
			}

			LoadResult.Page(
				data = repos,
				prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
				nextKey = nextKey
			)
		} catch (exception: IOException) {
			LoadResult.Error(exception)
		} catch (exception: HttpException) {
			LoadResult.Error(exception)
		}
	}
}
