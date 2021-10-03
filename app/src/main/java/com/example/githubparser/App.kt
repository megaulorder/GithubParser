package com.example.githubparser

import android.app.Application
import com.example.githubparser.data.api.GithubService
import com.example.githubparser.data.repository.ReposRepository
import com.example.githubparser.ui.repository.SearchReposViewModel

class App : Application() {

	lateinit var reposRepository: ReposRepository
	lateinit var searchReposViewModel: SearchReposViewModel

	override fun onCreate() {
		super.onCreate()
		instance = this

		reposRepository = ReposRepository(GithubService.create())
	}

	companion object {
		lateinit var instance: App
	}
}