package com.example.androidretrofitdemo.repository

import com.example.androidretrofitdemo.api.RetrofitInstance
import com.example.androidretrofitdemo.model.PostModel
import retrofit2.Response

class Repository {

    suspend fun getFirstPost(): Response<PostModel> {
        return RetrofitInstance.api.getFirstPost()
    }

    suspend fun getPostById(id: Int): Response<PostModel> {
        return RetrofitInstance.api.getPostById(id)
    }

    suspend fun getPostsByUserId(userId: Int, sort: String, order: String): Response<List<PostModel>> {
        return RetrofitInstance.api.getPostsByUserId(userId, sort, order)
    }

    suspend fun getPostsByUserIdByMapOfOptions(
        userId: Int, options: Map<String, String>): Response<List<PostModel>> {

        return RetrofitInstance.api.getPostsByUserIdByMapOfOptions(userId, options)
    }

}