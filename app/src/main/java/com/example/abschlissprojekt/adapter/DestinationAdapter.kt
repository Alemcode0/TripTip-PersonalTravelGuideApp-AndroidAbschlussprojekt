package com.example.abschlissprojekt.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.ListItemHomeBinding
import com.example.abschlissprojekt.ui.HomeDetailFragmentArgs
import com.example.abschlissprojekt.ui.HomeFragmentDirections


class DestinationAdapter(
    private var dataset: List<Destination>,
    private val viewModel: MainViewModel
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
        holder.binding.cbHeart.isChecked = currentDestination.favourite

        // ClickListener für den Favourite-Button
        holder.binding.cbHeart.setOnCheckedChangeListener { _, isChecked ->
            currentDestination.favourite = isChecked
            viewModel.updateDestination(currentDestination)
            if (isChecked) {
                Toast.makeText(holder.itemView.context, "Added to Favourites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(holder.itemView.context, "Removed from Favourites", Toast.LENGTH_SHORT).show()
            }
        }

        holder.binding.listItemHome.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(
                currentDestination.name,
                destinationImage1 = currentDestination.imageUrl,
                destinationImage2 = currentDestination.imageUrl1,
                destinationImage3 = currentDestination.imageUrl2,
                destinationImage4 = currentDestination.imageUrl3,
                destinationImage5 = currentDestination.imageUrl4,
            )
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDestinations(destinations: List<Destination>) {
        this.dataset = destinations
        notifyDataSetChanged()
    }
}