package com.example.artbook.data.source.remote.impl

import com.example.artbook.data.model.request.ExampleRequest
import com.example.artbook.data.model.response.ExampleResponse
import com.example.artbook.data.service.ExampleService
import com.example.artbook.data.source.remote.ExampleRemoteDataSource

class ExampleRemoteDataSourceImpl (
    private val exampleService: ExampleService,
)  : ExampleRemoteDataSource {

    override suspend fun postExample(
        exampleRequest: ExampleRequest
    ): ExampleResponse =
        exampleService.postExample(exampleRequest)
}