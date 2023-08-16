package com.example.artbook.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbook.presentation.home.*
import javax.inject.Inject

class FragmentFactory @Inject constructor(
    private val artAdapter: ArtAdapter,
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ArtFragment::class.java.name -> ArtFragment(artAdapter)
            SearchImgFragment::class.java.name -> SearchImgFragment(imageAdapter)
            WriteArtItemFragment::class.java.name -> WriteArtItemFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
