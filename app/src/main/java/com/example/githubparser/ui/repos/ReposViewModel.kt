package com.example.githubparser.ui.repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubparser.data.model.Repo
import com.example.githubparser.data.repository.ReposRepository
import kotlinx.coroutines.flow.Flow

class ReposViewModel(
	private val repository: ReposRepository,
	private val queryString: String
) : ViewModel() {

	private lateinit var _reposFlow: Flow<PagingData<Repo>>

	val reposFlow: Flow<PagingData<Repo>>
		get() = _reposFlow

	init {
		Log.d("GithubRepository", "ViewModel: query : $queryString")

		searchRepos(queryString)
	}

	private fun searchRepos(queryString: String) {
		_reposFlow = repository.search(queryString)
			.cachedIn(viewModelScope)
	}
}

class ViewModelFactory(
	private val repository: ReposRepository,
	private val queryString: String
) : ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return ReposViewModel(repository, queryString) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
