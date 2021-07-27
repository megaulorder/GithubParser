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
    private val _login = MutableLiveData<String>()
    private val _repositoryName = MutableLiveData<String>()

    private var _repositoryPicker = MutableLiveData<RepositoryPicker>()

    private val _repository = _repositoryPicker.switchMap { path ->
        repositoryRepository.getRepository(
            path.login,
            path.repositoryName
        )
    }

    val repository: LiveData<Resource<Repository?>> = _repository

    fun start(login: String, repositoryName: String) {
        _login.value = login
        _repositoryName.value = repositoryName
        _repositoryPicker.value = RepositoryPicker(_login.value!!, _repositoryName.value!!)
    }
}

class RepositoryPicker(login: String, repositoryName: String) {
    var login: String
    var repositoryName: String

    init {
        this.login = login
        this.repositoryName = repositoryName
    }
}