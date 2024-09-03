package com.example.abschlissprojekt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.ListItemCommunityStatusBinding

class ProfileCommunityAdapter(private var dataset: List<Destination>
) : RecyclerView.Adapter<ProfileCommunityAdapter.ProfileCommunityViewHolder>() {
    inner class ProfileCommunityViewHolder(val binding: ListItemCommunityStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCommunityViewHolder {
        val binding = ListItemCommunityStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileCommunityViewHolder(binding)

    }

    override fun getItemCount() =
        dataset.size

    override fun onBindViewHolder(holder: ProfileCommunityViewHolder, position: Int) {

    }
}
