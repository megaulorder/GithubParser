package com.example.githubparser.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos")
data class Repo(
	@PrimaryKey @ColumnInfo(name = "repoId") val id: Long,
	val name: String,
	@SerializedName("full_name") val fullName: String,
	val description: String?,
	val language: String?,
	@field:SerializedName("stargazers_count") val stars: Int,
	@Embedded val owner: Owner,
	val ownerId: Long
)

@Entity(tableName = "owners")
data class Owner(
	@PrimaryKey val id: Long,
	val login: String,
	@SerializedName("avatar_url") val avatarUrl: String,
)

data class OwnerWithRepos(
	@Embedded val owner: Owner,
	@Relation(
		parentColumn = "id",
		entityColumn = "ownerId"
	)
	val repos: List<Repo>
)
