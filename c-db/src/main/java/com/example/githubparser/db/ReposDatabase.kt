package com.example.githubparser.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
	entities = [Repo::class, PageKeys::class],
	version = 1,
	exportSchema = false
)
abstract class ReposDatabase : RoomDatabase() {

	abstract fun reposDao(): ReposDao
	abstract fun pageKeysDao(): PageKeysDao

	companion object {

		@Volatile
		private var INSTANCE: ReposDatabase? = null

		fun getDatabase(context: Context): ReposDatabase =
			INSTANCE ?: synchronized(this) {
				INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
			}

		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(
				context.applicationContext,
				ReposDatabase::class.java, "Github.db"
			)
				.build()
	}
}
