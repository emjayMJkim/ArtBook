package com.example.artbook.data.service

import com.example.artbook.data.model.response.ResponseImage
import com.example.artbook.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY,
    ): Response<ResponseImage>
}
