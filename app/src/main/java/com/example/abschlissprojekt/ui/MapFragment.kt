package com.example.abschlissprojekt.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.mapFragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        //googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_styles))
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL


        val latLng = LatLng(51.5167, 9.9167)
        val markerOptions = MarkerOptions()
        googleMap.addMarker(MarkerOptions().position(latLng).title("Germany"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        googleMap.addMarker(markerOptions)

        googleMap.uiSettings.isMyLocationButtonEnabled = true

        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isTiltGesturesEnabled = true
        googleMap.isBuildingsEnabled = true


        binding.radioGroup.setOnCheckedChangeListener { _, itemId: Int ->
            when (itemId) {
                R.id.normalBtn -> {
                    googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                }

                R.id.satelliteBtn -> {
                    googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                }

                R.id.hybridBtn -> {
                    googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                }

                R.id.terrainBtn -> {
                    googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                }

            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        return binding.root
    }
}

