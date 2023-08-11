package com.example.artbook.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.artbook.R
import com.example.artbook.base.BaseFragment
import com.example.artbook.databinding.FragmentWriteArtItemBinding

class WriteArtItemFragment : BaseFragment<FragmentWriteArtItemBinding>(R.layout.fragment_write_art_item) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgChooseArt.setOnClickListener {
            findNavController().navigate(
                WriteArtItemFragmentDirections.actionWriteArtItemFragmentToSearchImgFragment(),
            )
        }

        callback()
    }

    private fun callback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}
