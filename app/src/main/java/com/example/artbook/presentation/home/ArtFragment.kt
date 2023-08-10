package com.example.artbook.presentation.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.artbook.R
import com.example.artbook.base.BaseFragment
import com.example.artbook.databinding.FragmentArtBinding

class ArtFragment : BaseFragment<FragmentArtBinding>(R.layout.fragment_art) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabFragmentArt.setOnClickListener {
            findNavController().navigate(
                ArtFragmentDirections.actionArtFragmentToWriteArtItemFragment(),

            )
        }
    }
}
