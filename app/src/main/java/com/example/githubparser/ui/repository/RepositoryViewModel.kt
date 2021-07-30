package com.example.githubparser.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.data.repository.RepositoryRepository
import com.example.githubparser.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {
    private val _query = MutableLiveData<String?>()

    private val _repositories = _query.switchMap { query ->
            repositoryRepository.getRepositories(query!!)
    }

    val repositories: LiveData<Resource<RepositoriesList?>> = _repositories

    fun start(query: String) {
        _query.value = query
    }
}