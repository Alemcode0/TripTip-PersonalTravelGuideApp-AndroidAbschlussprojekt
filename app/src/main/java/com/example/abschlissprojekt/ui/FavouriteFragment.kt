package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.adapter.DestinationAdapter
import com.example.abschlissprojekt.adapter.FavouriteAdapter
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.FragmentFavouriteBinding
import com.example.abschlissprojekt.databinding.FragmentHomeBinding

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = listOf<Destination>()
        val adapter = FavouriteAdapter(emptyList())

        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)

//        viewModel.allFavourites.observe(viewLifecycleOwner) { favourites ->
//            val adapter = FavouriteAdapter(emptyList())
//            adapter.setDestinations(favourites)
//        }


    }
}