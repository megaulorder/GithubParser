package com.example.githubparser

import android.app.Application
import com.example.githubparser.data.repository.ReposRepository
import com.example.githubparser.data.service.GithubApi
import com.example.githubparser.db.ReposDatabase

class App : Application() {

	lateinit var reposRepository: ReposRepository

	override fun onCreate() {
		super.onCreate()
		instance = this

		reposRepository = ReposRepository(GithubApi.create(), ReposDatabase.getDatabase(this))
	}

	companion object {
		lateinit var instance: App
	}
}
