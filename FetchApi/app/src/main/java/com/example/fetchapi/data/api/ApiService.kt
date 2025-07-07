package com.example.fetchapi.data.api

import com.example.fetchapi.data.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")

    suspend fun getPosts(): List<Post>;
}