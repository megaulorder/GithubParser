package com.example.githubparser.ui.repository

import androidx.lifecycle.ViewModel
import com.example.githubparser.data.repository.RepositoryRepository

class RepositoryViewModel : ViewModel() {
    val repositoriesListCall = RepositoryRepository().parseJson()
}