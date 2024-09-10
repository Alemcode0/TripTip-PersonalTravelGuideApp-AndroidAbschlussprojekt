package com.example.abschlissprojekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.adapter.DestinationAdapter
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var destinationAdapter: DestinationAdapter

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
        val adapter = DestinationAdapter(imageList, viewModel)
        destinationAdapter = DestinationAdapter(emptyList(), viewModel)

        binding.rvHome.adapter = destinationAdapter
        binding.rvHome.layoutManager = CarouselLayoutManager()
        binding.rvHome.setHasFixedSize(true)
        CarouselSnapHelper().attachToRecyclerView(binding.rvHome)

        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        // Daten laden und an den Adapter übergeben
        viewModel.destinationList.observe(viewLifecycleOwner) { destinations ->
            adapter.setDestinations(destinations)
        }

        viewModel.destinationList.observe(
            viewLifecycleOwner) {
            binding.rvHome.adapter = adapter
        }

        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchDestinations(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchDestinations(it)
                }
                return false
            }
        })
    }

    private fun searchDestinations(query: String) {
        viewModel.searchDestinations(query).observe(viewLifecycleOwner) { destinations ->
            if (destinations.isEmpty()) {
                Log.d("HomeFragment", "No destinations found.")
            } else {
                Log.d("HomeFragment", "Found ${destinations.size} destinations.")
            }
            destinationAdapter.setDestinations(destinations)
        }
    }

}