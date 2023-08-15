package com.example.artbook.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbook.presentation.home.WriteArtItemFragment
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            WriteArtItemFragment::class.java.name -> WriteArtItemFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
