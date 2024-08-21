package com.example.abschlissprojekt.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.ListItemHomeBinding
import com.example.abschlissprojekt.ui.HomeFragmentDirections


class DestinationAdapter(private var dataset: List<Destination>
) : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {

    inner class DestinationViewHolder(val binding: ListItemHomeBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ListItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding)
    }

    override fun getItemCount() =
        dataset.size

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val currentDestination = dataset[position]

        holder.binding.ivHome.setImageResource(currentDestination.imageUrl)
        holder.binding.tvPlaceName.text = currentDestination.name

        holder.binding.listItemHome.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(
                currentDestination.name, currentDestination.imageUrl )
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDestinations(destinations: List<Destination>) {
        this.dataset = destinations
        notifyDataSetChanged()
    }


}