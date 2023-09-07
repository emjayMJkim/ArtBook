package com.example.artbook.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artbook.R
import com.example.artbook.base.BaseFragment
import com.example.artbook.databinding.FragmentArtBinding
import javax.inject.Inject

class ArtFragment @Inject constructor(
    private val artAdapter: ArtAdapter,
) : BaseFragment<FragmentArtBinding>(R.layout.fragment_art) {

    lateinit var viewModel: ArtViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artAdapter.arts[layoutPosition]

            viewModel.deleteArt(selectedArt)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        subscribeToObserves()

        binding.recyclerviewArt.adapter = artAdapter
        binding.recyclerviewArt.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerviewArt)

        binding.fabFragmentArt.setOnClickListener {
            findNavController().navigate(
                ArtFragmentDirections.actionArtFragmentToWriteArtItemFragment(),

            )
        }
    }

    private fun subscribeToObserves() {
        viewModel.artList.observe(
            viewLifecycleOwner,
            Observer {
                artAdapter.arts = it
            },
        )
    }
}
