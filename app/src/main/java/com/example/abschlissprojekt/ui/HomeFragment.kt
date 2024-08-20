package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.adapter.DestinationAdapter
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = listOf<Destination>()
        val adapter = DestinationAdapter(imageList)

        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = CarouselLayoutManager()
        binding.rvHome.setHasFixedSize(true)
        CarouselSnapHelper().attachToRecyclerView(binding.rvHome)

        //viewModel.insert()

        viewModel.destinationList.observe(
            viewLifecycleOwner) {
            binding.rvHome.adapter = DestinationAdapter(it)
        }
    }
}