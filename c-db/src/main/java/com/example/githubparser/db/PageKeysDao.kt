package com.example.githubparser.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageKeysDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(pageKey: List<PageKeys>)

	@Query("SELECT * FROM page_keys WHERE repoId = :repoId")
	suspend fun pageKeysByRepoId(repoId: Long): PageKeys?

	@Query("DELETE FROM page_keys")
	suspend fun clearPageKeys()
}
