package com.example.abschlissprojekt.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.abschlissprojekt.MainViewModel
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.ListItemFavouriteBinding

class FavouriteAdapter(
    private var dataset: List<Destination>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    inner class FavouriteViewHolder(val binding: ListItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding =
            ListItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val currentDestination = dataset[position]

        holder.binding.ivDestinationImg.setImageResource(currentDestination.imageUrl)
        holder.binding.tvDestinationName.text = currentDestination.name
        holder.binding.tvLocation.text = currentDestination.location
        holder.binding.favouriteBtn.isChecked = currentDestination.favourite

        holder.binding.favouriteBtn.setOnCheckedChangeListener { checkBox, isChecked ->
            currentDestination.favourite = isChecked
            viewModel.updateDestination(currentDestination)  // Methode zum Aktualisieren in der DB
            if (isChecked) {
                showToast(holder,"Destination added to Favourite")
            } else {
                showToast(holder,"Destination removed from Favourite")
            }
        }
    }

    private fun showToast(holder: FavouriteViewHolder, message: String) {
        val context = holder.itemView.context
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDestinations(destinations: List<Destination>) {
        this.dataset = destinations
        notifyDataSetChanged()
    }

}