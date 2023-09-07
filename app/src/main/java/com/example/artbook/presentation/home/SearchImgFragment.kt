package com.example.artbook.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artbook.R
import com.example.artbook.base.BaseFragment
import com.example.artbook.databinding.FragmentSearchImgBinding
import com.example.artbook.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchImgFragment @Inject constructor(
    private val imageAdapter: ImageAdapter,
) : BaseFragment<FragmentSearchImgBinding>(R.layout.fragment_search_img) {

    lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        // for delay
        var job: Job? = null
        binding.edtSearchImg.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()

        binding.rcvSearchImg.adapter = imageAdapter
        binding.rcvSearchImg.layoutManager = GridLayoutManager(requireContext(), 3)
        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    fun subscribeToObservers() {
        viewModel.imageList.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        val url = it.data?.hits?.map { resultImg ->
                            resultImg.previewURL
                        }
                        imageAdapter.images = url ?: listOf()

                        binding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            },
        )
    }
}
