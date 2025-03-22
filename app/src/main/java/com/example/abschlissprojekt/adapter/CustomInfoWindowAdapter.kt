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

        val title: TextView = view.findViewById(R.id.infoWindowTitle)
        val image: ImageView = view.findViewById(R.id.infoWindowIv)

        val data = marker.tag as? CustomInfoWindow

        if (data != null) {
            title.text = data.title
            image.setImageResource(data.image)
        } else {
            title.text = marker.title
            image.setImageResource(R.drawable.travelberlin)
        }

        return view
    }
}