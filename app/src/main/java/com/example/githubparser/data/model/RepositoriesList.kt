package com.example.githubparser.data.model

import com.google.gson.annotations.SerializedName

data class RepositoriesList(
    @SerializedName("items")
    var repositories: List<Repository>? = null
)