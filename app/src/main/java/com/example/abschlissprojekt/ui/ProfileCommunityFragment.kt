package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.CommunityViewModel
import com.example.abschlissprojekt.adapter.ProfileCommunityAdapter
import com.example.abschlissprojekt.data.models.Community
import com.example.abschlissprojekt.databinding.FragmentProfileCommunityBinding

class ProfileCommunityFragment : Fragment() {

    private lateinit var binding: FragmentProfileCommunityBinding
    private val viewModel: CommunityViewModel by activityViewModels()
    private lateinit var adapter: ProfileCommunityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Verwende den inflater und container für das Binding
        binding = FragmentProfileCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisiere den Adapter mit einer leeren Liste
        adapter = ProfileCommunityAdapter(emptyList())

        // Adapter und LayoutManager für die RecyclerView
        binding.rvContacts.adapter = adapter
        binding.rvContacts.layoutManager = LinearLayoutManager(context)

        // die Daten beobachten und aktualisiere den Adapter
        viewModel.allCommunities.observe(viewLifecycleOwner) { communities ->
            // Update den Adapter mit der neuen Liste
            adapter = ProfileCommunityAdapter(communities)
            binding.rvContacts.adapter = adapter
            binding.rvContacts.setHasFixedSize(true)
        }
    }
}