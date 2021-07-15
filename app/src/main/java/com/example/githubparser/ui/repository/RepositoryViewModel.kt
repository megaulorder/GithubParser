package com.example.githubparser.ui.repository

import androidx.lifecycle.ViewModel
import com.example.githubparser.data.repository.RepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {
    val repositoriesListCall = repositoryRepository.parseJson()
}