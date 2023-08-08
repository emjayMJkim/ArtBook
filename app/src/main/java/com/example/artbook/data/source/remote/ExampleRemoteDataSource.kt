package com.example.artbook.data.source.remote

import com.example.artbook.data.model.request.ExampleRequest
import com.example.artbook.data.model.response.ExampleResponse

interface ExampleRemoteDataSource {

    suspend fun postExample(
        exampleRequestDto: ExampleRequest,
    ): ExampleResponse
}
