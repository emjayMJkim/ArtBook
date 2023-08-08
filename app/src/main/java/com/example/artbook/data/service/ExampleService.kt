package com.example.artbook.data.service

import com.example.artbook.data.model.request.ExampleRequest
import com.example.artbook.data.model.response.ExampleResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ExampleService {
    @POST("api/example")
    suspend fun postExample(
        @Body request: ExampleRequest,
    ): ExampleResponse
}