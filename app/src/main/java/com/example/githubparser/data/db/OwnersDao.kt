package com.example.githubparser.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.githubparser.data.model.Owner
import com.example.githubparser.data.model.OwnerWithRepos

interface OwnersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(owners: List<Owner>)

	@Transaction
	@Query("SELECT * FROM owners WHERE id = :ownerId")
	suspend fun ownerByOwnerId(ownerId: Long): OwnerWithRepos?

	@Query("DELETE FROM owners")
	suspend fun clearOwners()
}