package com.example.githubparser.ui.main.viewmodel

import androidx.databinding.ObservableField
import com.example.githubparser.data.model.Repository

class RepositoryViewModel(private val repository: Repository) {
    val info = ObservableField("${repository.name} is ${repository.count} ")

    fun increment() {
        ++repository.count
        info.set("${repository.name} is ${repository.count} ")
    }
}