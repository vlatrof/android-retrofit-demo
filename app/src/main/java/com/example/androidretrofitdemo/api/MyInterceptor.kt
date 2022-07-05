package com.example.androidretrofitdemo.api

import okhttp3.Interceptor
import okhttp3.Response

// В нашем случае мы используем Interceptor для того чтобы перед отправкой
// добавить headers в наши http GET запросы
class MyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("X-Platform", "Android")
            .addHeader("X-Auth-Token", "123456789")
            .build()
        return chain.proceed(request)
    }

}