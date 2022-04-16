package com.examples.dagger.hilt.services

import retrofit2.http.GET

interface BlogService {
    @GET(BLOGS_REST_PATH)
    suspend fun getAllBlogs(): List<BlogNetworkEntity>
    companion object {
       const val BLOGS_REST_PATH = "blogs"
    }
}