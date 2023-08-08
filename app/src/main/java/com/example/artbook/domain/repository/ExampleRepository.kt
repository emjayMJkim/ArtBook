package com.example.artbook.domain.repository

import com.example.artbook.data.model.request.ExampleRequest
import com.example.artbook.data.model.response.ExampleResponse

interface ExampleRepository {

    suspend fun postExample(exampleRequest: ExampleRequest): Result<ExampleResponse>
}
