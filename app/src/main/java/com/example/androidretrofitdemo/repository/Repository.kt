package com.example.androidretrofitdemo.repository

import com.example.androidretrofitdemo.api.RetrofitInstance
import com.example.androidretrofitdemo.model.PostModel
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<PostModel> {
        return RetrofitInstance.api.getPost()
    }

}