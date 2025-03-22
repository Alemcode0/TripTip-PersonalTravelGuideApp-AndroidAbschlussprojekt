package com.example.abschlissprojekt.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abschlissprojekt.CommunityViewModel
import com.example.abschlissprojekt.FirebaseViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.adapter.ProfileCommunityAdapter
import com.example.abschlissprojekt.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: CommunityViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()
    private lateinit var adapter: ProfileCommunityAdapter

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.ivBackgroundPic.setImageURI(uri)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btLogout.setOnClickListener {
            firebaseViewModel.logout()
            findNavController().navigate(R.id.loginFragment)
        }

        adapter = ProfileCommunityAdapter(emptyList())

        binding.rvContacts.adapter = adapter
        binding.rvContacts.layoutManager = LinearLayoutManager(context)

        viewModel.allCommunities.observe(viewLifecycleOwner) { communities ->
            adapter = ProfileCommunityAdapter(communities)
            binding.rvContacts.adapter = adapter
            binding.rvContacts.setHasFixedSize(true)
        }

        binding.btUpdateStatus.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }
}