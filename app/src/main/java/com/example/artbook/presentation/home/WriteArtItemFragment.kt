package com.example.artbook.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.base.BaseFragment
import com.example.artbook.databinding.FragmentWriteArtItemBinding
import com.example.artbook.util.Status
import javax.inject.Inject

class WriteArtItemFragment @Inject constructor(
    val glide: RequestManager,
) : BaseFragment<FragmentWriteArtItemBinding>(R.layout.fragment_write_art_item) {

    lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        subscribeToObservers()

        binding.imgChooseArt.setOnClickListener {
            findNavController().navigate(
                WriteArtItemFragmentDirections.actionWriteArtItemFragmentToSearchImgFragment(),
            )
        }

        callback()

        binding.btnSaveItemArt.setOnClickListener {
            viewModel.makeArt(binding.edtArtName.text.toString(), binding.edtArtistName.text.toString(), binding.edtYear.text.toString())
        }
    }

    private fun callback() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(
            viewLifecycleOwner,
            Observer { url ->
                binding.let { binding ->
                    glide.load(url).into(binding.imgChooseArt)
                }
            },
        )

        viewModel.insertArtMessage.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                        findNavController().popBackStack()
                        viewModel.resetInsertArtMsg()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            },
        )
    }
}
