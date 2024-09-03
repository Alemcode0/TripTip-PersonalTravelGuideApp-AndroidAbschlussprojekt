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
import com.example.abschlissprojekt.databinding.FragmentCommunityStatusDetailBinding

class CommunityStatusDetailFragment : Fragment() {

    private lateinit var binding: FragmentCommunityStatusDetailBinding
    //private val args: StatusDetailFragmentArgs by navArgs()

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

//        (activity as MainActivity).supportActionBar?.title =
//            args.name +
//                    if (args.name.last() == 's') {
//                        "' "
//                    } else {
//                        "'s "
//                    } +
//                    "Status"
//
//        binding.tvUsername.text = args.name
////        val imageProfile = args.image
//        binding.ivStatusDetailImg.load(args.image)
//        binding.ivBackground.load(args.status)
//        binding.iBtnBack.setOnClickListener {
//            findNavController().navigateUp()
//        }
//
    }
}