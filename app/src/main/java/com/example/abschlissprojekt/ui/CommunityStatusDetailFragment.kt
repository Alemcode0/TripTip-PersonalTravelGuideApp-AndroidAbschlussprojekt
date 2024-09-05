package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.abschlissprojekt.MainActivity
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.data.exampleData.DestinationExampleData
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.FragmentCommunityStatusDetailBinding

class CommunityStatusDetailFragment : Fragment() {

    private lateinit var binding: FragmentCommunityStatusDetailBinding
    private val args: CommunityStatusDetailFragmentArgs by navArgs()
    private lateinit var destination: Destination

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityStatusDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val imageStatus = arguments?.getInt("imageResId") ?: 0
        val imageStatus = args.imageStatus
        val image = args.image


        (activity as MainActivity).supportActionBar?.title =
            args.name +
                    if (args.name.last() == 's') {
                        "' "
                    } else {
                        "'s "
                    } +
                    "Status"

        binding.tvUsername.text = args.name
        binding.tvDescription.text = args.statusDescription
        binding.ivStatusDetailImg.setImageResource(image)
        binding.ivBackground.setImageResource(imageStatus)

        val cityName = destination.name

        binding.ivStatusDetailImg.setOnClickListener {
            val action = CommunityStatusDetailFragmentDirections
                .actionCommunityStatusDetailFragmentToMapFragment(cityName)
            findNavController().navigate(action)
        }

        binding.iBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }
}