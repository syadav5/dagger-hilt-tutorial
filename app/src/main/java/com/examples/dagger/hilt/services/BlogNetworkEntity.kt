package com.examples.dagger.hilt.services

import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(
    @SerializedName("body") val body: String,
    @SerializedName("category") val category: String,
    @SerializedName("image") val image: String,
    @SerializedName("pk") val id: Int,
    @SerializedName("title") val title: String
)