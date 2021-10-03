package com.example.githubparser.data.api

import com.example.githubparser.data.model.Repo

sealed class ReposSearchResult {
	data class Success(val data: List<Repo>) : ReposSearchResult()
	data class Error(val error: Exception) : ReposSearchResult()
}