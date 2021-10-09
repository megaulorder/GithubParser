package com.example.githubparser

import android.app.Application
import com.example.githubparser.data.repository.ReposRepository
import com.example.githubparser.data.service.GithubApi
import com.example.githubparser.ui.repository.ReposViewModel

class App : Application() {

	lateinit var reposRepository: ReposRepository
	lateinit var reposViewModel: ReposViewModel

	override fun onCreate() {
		super.onCreate()
		instance = this

		reposRepository = ReposRepository(GithubApi.create())
	}

	companion object {
		lateinit var instance: App
	}
}
