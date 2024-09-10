package com.example.abschlissprojekt.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.data.models.Community
import com.example.abschlissprojekt.databinding.ListItemCommunityStatusBinding
import com.example.abschlissprojekt.ui.ProfileFragmentDirections
import com.google.android.gms.maps.model.LatLng

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
        // Sortiere die Liste ob imageStatus leer ist oder nicht.
        val communitySortedByStatus = dataset.sortedBy { it.imageStatus.isEmpty() }
        val contact = communitySortedByStatus[position]

        // Hauptbild und den Namen
        holder.binding.ivStatusImg.setImageResource(contact.image)
        holder.binding.tvStatusName.text = contact.name

        // Überprüfen, ob imageStatus nicht leer ist, um entsprechend darauf zu reagieren.
        if (contact.imageStatus.isNotEmpty()) {
            holder.binding.communityStatusItem.setOnClickListener {
                val latLng = LatLng(52.5200, 13.4050)
                val lat: Double = latLng.latitude  // Konvertierung in Float
                val lng: Double = latLng.longitude
                holder.itemView.findNavController().navigate(
                    ProfileFragmentDirections.actionProfileCommunityFragmentToCommunityStatusDetailFragment(
                        contact.image,
                        contact.name,
                        contact.statusDescription,
                        contact.cityName,
                        contact.latitude.toFloat(),
                        contact.longitude.toFloat(),
                        contact.imageStatus.first()

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
