package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.FragmentHomeBinding
import com.example.abschlissprojekt.databinding.FragmentHomeDetailBinding

class HomeDetailFragment : Fragment() {

    private lateinit var binding: FragmentHomeDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: HomeDetailFragmentArgs by navArgs()
    private lateinit var imageUrls: List<String>
    private lateinit var destination: Destination

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
        val imageResource1 = args.destinationImage1
        val imageResource2 = args.destinationImage2
        val imageResource3 = args.destinationImage3
        val imageResource4 = args.destinationImage4
        val imageResource5 = args.destinationImage5

        binding.tvHomeDetailName.text = stringResource
        binding.ivHomeDetail.setImageResource(imageResource1)
        binding.ivGallery1.setImageResource(imageResource2)
        binding.ivGallery2.setImageResource(imageResource3)
        binding.ivGallery3.setImageResource(imageResource4)
        binding.ivGallery4.setImageResource(imageResource5)

        binding.ivGallery1.setOnClickListener {
            binding.ivHomeDetail.setImageResource(imageResource2)
        }

        binding.ivGallery2.setOnClickListener {
            binding.ivHomeDetail.setImageResource(imageResource3)
        }

        binding.ivGallery3.setOnClickListener {
            binding.ivHomeDetail.setImageResource(imageResource4)
        }

        binding.ivGallery4.setOnClickListener {
            binding.ivHomeDetail.setImageResource(imageResource5)
        }

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

        destination = Destination(
            name = "Favourite Destination",
            description = "Description",
            imageUrl = R.drawable.triptoparis,
            imageUrl1 = R.drawable.travelparis1,
            imageUrl2 = R.drawable.travelparis2,
            imageUrl3 = R.drawable.travelparis3,
            imageUrl4 = R.drawable.travelparis4,
            location = "Location",
            favourite = false,
            latitude = 0.0,
            longitude = 0.0
        )

        binding.cbHeart.setOnClickListener {
            if (destination.favourite) {
                destination.favourite = false
                viewModel.removeFavourite(destination)
                showToast("Destination removed from Favourites")
            } else {
                destination.favourite = true
                viewModel.addFavourite(destination)
                showToast("Destination added to Favourites")
            }
            updateFavouriteButton()
        }
        updateFavouriteButton()
    }

    private fun updateFavouriteButton() {
        binding.cbHeart.isChecked = destination.favourite
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}