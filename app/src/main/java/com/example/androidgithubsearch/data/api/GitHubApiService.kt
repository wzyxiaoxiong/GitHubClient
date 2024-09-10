package com.example.androidgithubsearch.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubApiService {
    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): List<GitHubUserRepositoryResponse>

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String, @Query("page") page: Int): GitHubSearchRepositoryResponse

    @POST("login/oauth/authorize")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String?,
        @Field("scope") clientSecret: String?,
        @Field("state") username: String?
    ): Call<ResponseBody?>?
}
