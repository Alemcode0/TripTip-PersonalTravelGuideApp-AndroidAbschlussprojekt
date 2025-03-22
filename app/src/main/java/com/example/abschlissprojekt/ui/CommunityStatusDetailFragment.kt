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

        val imageStatus = args.imageStatus
        val image = args.image

        // Setzt den Titel der ActionBar abhängig vom übergebenen Namen
        (activity as MainActivity).supportActionBar?.title =
            args.name +
                    if (args.name.last() == 's') {
                        "' "
                    } else {
                        "'s "
                    } +
                    "Status"

        // Setzt den Namen, die Stadt und die Statusbeschreibung auf den entsprechenden Views
        binding.tvUsername.text = args.name
        binding.tvCityName.text = args.cityName
        binding.tvDescription.text = args.statusDescription
        binding.ivStatusDetailImg.setImageResource(image)
        binding.ivBackground.setImageResource(imageStatus)

        // Holt Stadtname, Latitude und Longitude aus den Argumenten
        val cityName = args.cityName
        val lat = args.latitude
        val lng = args.longitude

        // Bei Klick auf das Hintergrundbild navigiert die App zur MapFragment und übergibt Stadtname, Koordinaten und Statusbild
        binding.ivBackground.setOnClickListener {
            val action = CommunityStatusDetailFragmentDirections
                .actionCommunityStatusDetailFragmentToMapFragment(
                    cityName, lat, lng, imageStatus)
            findNavController().navigate(action)
        }

        binding.iBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}