package com.example.abschlissprojekt.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.abschlissprojekt.data.models.Community
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.ListItemCommunityStatusBinding
import com.example.abschlissprojekt.ui.ProfileCommunityFragment
import com.example.abschlissprojekt.ui.ProfileCommunityFragmentDirections

class ProfileCommunityAdapter(private var dataset: List<Community>
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
        // Sortiere die Liste basierend darauf, ob imageStatus leer ist oder nicht.
        val communitySortedByStatus = dataset.sortedBy { it.imageStatus.isEmpty() }
        val contact = communitySortedByStatus[position]

        // Hauptbild und den Namen
        holder.binding.ivStatusImg.setImageResource(contact.image)
        holder.binding.tvStatusName.text = contact.name

        // Überprüfen, ob imageStatus nicht leer ist, um entsprechend darauf zu reagieren.
        if (contact.imageStatus.isNotEmpty()) {
            holder.binding.communityStatusItem.setOnClickListener {
                holder.itemView.findNavController().navigate(
                    ProfileCommunityFragmentDirections.actionProfileCommunityFragmentToCommunityStatusDetailFragment(
                        contact.image,
                        contact.name,
                        contact.imageStatus[0] // Zeigt das erste Bild in der Liste an
                    )
                )
            }
            // die Sättigung Bild auf normal
            holder.binding.ivStatusImg.colorFilter =
                ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(1f) })
        } else {
            // Keine Aktion bei Klick, wenn imageStatus leer ist
            holder.binding.communityStatusItem.setOnClickListener { }
            // Bild Sättigung auf grau
            holder.binding.ivStatusImg.colorFilter =
                ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
        }
    }
}
