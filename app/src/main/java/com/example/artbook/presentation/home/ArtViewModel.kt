package com.example.artbook.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbook.data.model.response.ResponseImage
import com.example.artbook.data.model.roomdb.Art
import com.example.artbook.domain.repository.ArtRepository
import com.example.artbook.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtViewModel @Inject constructor(
    private val repository: ArtRepository,
) : ViewModel() {

    // Art Fragment
    val artList = repository.getArt()

    // Write Art Item Fragment
    private val _imageList = MutableLiveData<Resource<ResponseImage>>()
    val imageList: LiveData<Resource<ResponseImage>>
        get() = _imageList

    private val _selectedImageUrl = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = _selectedImageUrl

    // Search Img Fragment
    private var _insertArtMessage = MutableLiveData<Resource<Art>>()
    val insertArtMessage: LiveData<Resource<Art>>
        get() = _insertArtMessage

    fun resetInsertArtMsg() {
        _insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url: String) {
        _selectedImageUrl.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            _insertArtMessage.postValue(Resource.error("Enter name, artist, year", null))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            _insertArtMessage.postValue(Resource.error("Year should be Int", null))
            return
        }

        val art = Art(name, artistName, yearInt, _selectedImageUrl.value ?: "")
        insertArt(art)
        setSelectedImage("")
        _insertArtMessage.postValue(Resource.success(art))
    }

    fun searchImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }

        _imageList.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            _imageList.value = response
        }
    }
}
