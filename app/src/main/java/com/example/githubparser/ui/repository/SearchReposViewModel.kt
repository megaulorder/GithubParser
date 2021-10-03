package com.example.githubparser.ui.repository

import android.util.Log
import androidx.lifecycle.*
import com.example.githubparser.data.api.ReposSearchResult
import com.example.githubparser.data.repository.ReposRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Thread.sleep

class SearchReposViewModel(
	private val repository: ReposRepository,
	private val queryString: String
) : ViewModel() {

	var state = MutableLiveData<ReposSearchResult>()

	init {
		val query = MutableLiveData(queryString)

		Log.d("GithubRepository", "ViewModel: query : ${query.value}")

		state = query
			.switchMap { _query ->
				liveData {
					val data = repository.getSearchResultStream(_query)
						.asLiveData(Dispatchers.Main)
					emitSource(data)
					Log.d("GithubRepository", "ViewModel: data : ${data.value}")
				}
			} as MutableLiveData<ReposSearchResult>

		state.postValue(state.value)
	}
}

class ViewModelFactory(
	private val repository: ReposRepository,
	private val queryString: String
) : ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SearchReposViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return SearchReposViewModel(repository, queryString) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
