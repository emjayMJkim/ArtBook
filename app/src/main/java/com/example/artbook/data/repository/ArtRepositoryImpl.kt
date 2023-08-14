package com.example.artbook.data.repository

import androidx.lifecycle.LiveData
import com.example.artbook.data.model.response.ResponseImage
import com.example.artbook.data.model.roomdb.Art
import com.example.artbook.data.service.ImageApiService
import com.example.artbook.data.source.local.ArtDao
import com.example.artbook.domain.repository.ArtRepository
import com.example.artbook.util.Resource
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artDao: ArtDao,
    private val apiService: ImageApiService
) : ArtRepository {

    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ResponseImage> {
        return try {
            val response = apiService.searchImage(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("데이터가 없습니다", null)
        }
    }
}
