package com.example.artbook.data.model.response

import com.google.gson.annotations.SerializedName

data class ResponseImage(
    val total: Int,
    val totalHits: Int,
    val hits: List<ResultImage>,
) {
    data class ResultImage(
        val id: Int,
        val pageURL: String,
        val type: String,
        val tags: String,
        val previewURL: String,
        val previewWidth: Int,
        val previewHeight: Int,
        val webformatURL: String,
        val webformatWidth: Int,
        val webformatHeight: Int,
        val largeImageURL: String,
        val imageWidth: Int,
        val imageHeight: Int,
        val imageSize: Int,
        val views: Int,
        val downloads: Int,
        val collections: Int,
        val likes: Int,
        val comments: Int,
        @SerializedName("user_id")
        val userId: Int,
        val user: String,
        val userImageURL: String,
    )
}
