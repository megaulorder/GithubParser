package com.example.githubparser.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
	val id: Long,
	val name: String,
	@SerializedName("full_name") val fullName: String,
	val description: String?,
	val language: String?,
	val owner: Owner
)

data class Owner(
	val login: String,
	@SerializedName("avatar_url") val avatar: String,
)
