package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.databinding.FragmentHomeBinding
import com.example.abschlissprojekt.databinding.FragmentHomeDetailBinding

class HomeDetailFragment : Fragment() {

    private lateinit var binding: FragmentHomeDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: HomeDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stringResource = args.myString
        val imageResource = args.destinationImage

        binding.tvHomeDetailName.text = stringResource
        binding.ivHomeDetail.setImageResource(imageResource)
        binding.ivBackIcon.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.tvRating.text = rating.toString()
            when (ratingBar.rating.toInt()) {
                1 -> binding.tvRating.text = "Very Bad"
                2 -> binding.tvRating.text = "Bad"
                3 -> binding.tvRating.text = "Good"
                4 -> binding.tvRating.text = "Great"
                5 -> binding.tvRating.text = "Awesome"
                else -> binding.tvRating.text = " "
            }
        }
    }
}