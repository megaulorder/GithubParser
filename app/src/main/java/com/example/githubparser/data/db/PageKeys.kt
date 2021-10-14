package com.example.githubparser.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page_keys")
data class PageKeys(
	@PrimaryKey
	val repoId: Long,
	val prevKey: Int?,
	val nextKey: Int?
)