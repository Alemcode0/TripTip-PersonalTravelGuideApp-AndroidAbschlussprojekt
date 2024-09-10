package com.example.abschlissprojekt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.data.exampleData.CustomInfoWindow
import com.example.abschlissprojekt.data.models.Destination
import com.example.abschlissprojekt.databinding.CustomInfoWindowBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(private val context : Context) : GoogleMap.InfoWindowAdapter {

    private lateinit var binding: CustomInfoWindowBinding
    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null)
        // Finde die Views in deinem Layout
        val title: TextView = view.findViewById(R.id.infoWindowTitle)
        val image: ImageView = view.findViewById(R.id.infoWindowIv)

        // Extrahiere die Daten aus dem Marker Tag
        val data = marker.tag as? CustomInfoWindow

        // Falls das Tag vorhanden ist, setze die entsprechenden Daten
        if (data != null) {
            title.text = data.title
            image.setImageResource(data.image)
        } else {
            // Optional: Fallback, falls die Daten nicht gesetzt wurden
            title.text = marker.title
            image.setImageResource(R.drawable.travelberlin)  // Setze ein Standardbild
        }

        return view
    }
}