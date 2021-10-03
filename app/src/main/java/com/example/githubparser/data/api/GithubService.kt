package com.example.githubparser.data.api

import com.example.githubparser.data.model.GithubResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

	@GET("search/repositories")
	suspend fun searchRepos(
		@Query("q") query: String
	): GithubResponse

	companion object {
		private const val BASE_URL = "https://api.github.com/"

		fun create(): GithubService {
			val logger = HttpLoggingInterceptor()
			logger.level = HttpLoggingInterceptor.Level.BASIC

			val client = OkHttpClient.Builder()
				.addInterceptor(logger)
				.build()
			return Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(GithubService::class.java)
		}
	}
}