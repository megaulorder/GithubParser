package com.example.githubparser.data.service

import com.example.githubparser.db.Repo
import com.google.gson.annotations.SerializedName

data class GithubResponse(
	@SerializedName("total_count") val total: Int = 0,
	var items: List<Repo>,
	val nextPage: Int? = null
)
