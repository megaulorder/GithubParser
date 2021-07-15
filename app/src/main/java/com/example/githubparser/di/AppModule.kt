package com.example.githubparser.di

import com.example.githubparser.data.api.RepositoryApi
import com.example.githubparser.data.model.RepositoriesList
import com.example.githubparser.utils.constants.UrlConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(UrlConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideRepositoryApi(retrofit: Retrofit): RepositoryApi =
        retrofit.create(RepositoryApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(repositoryApi: RepositoryApi): Call<RepositoriesList> =
        repositoryApi.getRepositories()
}
