package com.example.artbook.data.repository

import com.example.artbook.data.model.request.ExampleRequest
import com.example.artbook.data.model.response.ExampleResponse
import com.example.artbook.data.source.remote.ExampleRemoteDataSource
import com.example.artbook.domain.repository.ExampleRepository

class ExampleRepositoryImpl(
    private val exampleDataSource: ExampleRemoteDataSource,
) : ExampleRepository {
    override suspend fun postExample(exampleRequest: ExampleRequest): Result<ExampleResponse> =
        kotlin.runCatching { exampleDataSource.postExample(exampleRequest) }
}
