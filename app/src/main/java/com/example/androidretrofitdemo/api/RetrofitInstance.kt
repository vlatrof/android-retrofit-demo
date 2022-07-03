package com.example.androidretrofitdemo.api

import com.example.androidretrofitdemo.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ключевое слово object применяется здесь для реализации design паттерна "Singleton"
object RetrofitInstance {

    // by lazy: инициализация значения в данном поле произойдет только при первом обращении к нему
    // А первое обращение к полю retrofit будет произведено при первом же обращении к полю Api
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // вызов поля Api произойдет из Repository
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}