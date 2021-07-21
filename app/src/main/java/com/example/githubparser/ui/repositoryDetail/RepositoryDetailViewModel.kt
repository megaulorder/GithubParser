package com.example.githubparser.ui.repositoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.githubparser.data.model.Repository
import com.example.githubparser.data.repository.RepositoryRepository
import com.example.githubparser.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {
    private val _name = MutableLiveData<String>()

    private val _repository = _name.switchMap { name ->
        repositoryRepository.getRepository(name)
    }

    val repository: LiveData<Resource<Repository?>> = _repository


    fun start(name: String) {
        _name.value = name
    }
}