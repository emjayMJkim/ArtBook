package com.example.artbook.domain.repository

import androidx.lifecycle.LiveData
import com.example.artbook.data.model.response.ResponseImage
import com.example.artbook.data.model.roomdb.Art
import com.example.artbook.util.Resource

interface ArtRepository {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ResponseImage>
}
