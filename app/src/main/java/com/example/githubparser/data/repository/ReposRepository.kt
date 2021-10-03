package com.example.githubparser.data.repository

import android.util.Log
import com.example.githubparser.data.api.GithubService
import com.example.githubparser.data.api.ReposSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.HttpException
import java.io.IOException

class ReposRepository(private val service: GithubService) {

	private val searchResults = MutableSharedFlow<ReposSearchResult>(replay = 1)

	suspend fun getSearchResultStream(query: String): Flow<ReposSearchResult> {
		Log.d("GithubRepository", "ReposRepository: New query: $query")
		requestData(query)

		return searchResults
	}

	private suspend fun requestData(query: String) {

		try {
			val response = service.searchRepos(query)
			Log.d("GithubRepository", "ReposRepository: response $response")
			val repos = response.items
			searchResults.emit(ReposSearchResult.Success(repos))
		} catch (exception: IOException) {
			searchResults.emit(ReposSearchResult.Error(exception))
		} catch (exception: HttpException) {
			searchResults.emit(ReposSearchResult.Error(exception))
		}
	}
}
