package com.example.androidretrofitdemo.api

import com.example.androidretrofitdemo.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton
object RetrofitInstance {

    // создаем кастомный OkHttpClient с добавлением в него MyInterceptor
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(MyInterceptor())
    }.build()

    // by lazy: инициализация значения в данном поле произойдет только при первом обращении к нему
    // А первое обращение к полю retrofit будет произведено при первом же обращении к полю Api.
    //
    // Строка .client(client)
    // замещаем httpClient по умолчанию на наш созданный OkHttpClient с нашим MyInterceptor
    // для добавления ко ВСЕМ исходящим запросам список http заголовков
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // автоматическая генерация имплементации интерфейса SimpleApi
    // вызов поля Api произойдет из Repository
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}