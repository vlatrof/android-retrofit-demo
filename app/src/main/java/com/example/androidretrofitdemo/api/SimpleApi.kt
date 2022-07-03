package com.example.androidretrofitdemo.api

import com.example.androidretrofitdemo.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

// Реализацию для этого интерфейса мы не пишем
// Ее создаст автоматически retrofit
// мы будем иметь к ней доступ через RetrofitInstance.api
// в этом примере обращение к реализации происходит из репозитория
interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Response<PostModel>

}