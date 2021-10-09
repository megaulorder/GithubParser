package com.example.githubparser.data.model

import com.google.gson.annotations.SerializedName

data class GithubResponse(
	@SerializedName("total_count") val total: Int = 0,
	var items: List<Repo>,
	val nextPage: Int? = null
)
