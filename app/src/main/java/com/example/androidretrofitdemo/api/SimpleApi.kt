package com.example.androidretrofitdemo.api

import com.example.androidretrofitdemo.model.PostModel
import retrofit2.Response
import retrofit2.http.*

// Реализацию для этого интерфейса мы не пишем
// Ее создаст автоматически retrofit
// мы будем иметь к ней доступ через RetrofitInstance.api
// в этом примере обращение к реализации происходит из репозитория
interface SimpleApi {

    // Запрос для получения первого поста
    // В данном случае с помощью аннотации @Headers
    // мы добавляем заголовки в http запрос
    @Headers(
        "Authorization: 123123123",
        "Platform: Android",

    )
    @GET("posts/1")
    suspend fun getFirstPost(): Response<PostModel>

    // Запрос для получения поста по его ID
    // Аннотация Path заменит в пути запроса {postId} на указанное нами значение
    // В данном случае с помощью аннотации @Header мы добавляем заголовок
    // в http запрос динамически как параметр
    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") id: Int,
        @Header("Auth") auth: String
    ): Response<PostModel>

    // Запрос для полчения всех постов пользователя по его ID
    // Аннотация @Query("userId") добавит в конец запроса следующее: ?userId={переданный_userId}
    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
    ): Response<List<PostModel>>

    // Запрос ниже использует @QueryMap, в котором можно задавать пары параметров
    // Например _sort:id и _order:desc (2 пары) и т. д.
    // В данном случае такая форма записи сокращает предыдущую форму и дает возможности
    // для дальнейшего расширения списка параметров (options) запроса без изменения кода в данном интерфейсе
    @GET("posts")
    suspend fun getPostsByUserIdByMapOfOptions(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<PostModel>>

    // Стандартный POST-запрос по адресу base.url/posts/
    @POST("posts")
    suspend fun pushPost(
        @Body post: PostModel
    ): Response<PostModel>

    // POST-запрос по адресу base.url/posts/
    // данные отправляются как пары ключ значение
    // в строке запроса разделенные &
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPostFormUrlEncoded(
        @Field("id") id: Int,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<PostModel>



}