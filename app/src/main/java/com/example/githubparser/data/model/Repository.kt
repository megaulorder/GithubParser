package com.example.githubparser.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    val description: String?,
    val language: String?,
    val owner: Owner
)