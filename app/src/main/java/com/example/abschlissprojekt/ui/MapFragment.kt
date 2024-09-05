package com.example.abschlissprojekt.ui


import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.databinding.FragmentMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    private val args: MapFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(it)
        }!!
        fetchLocation()

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }



    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.setAllGesturesEnabled(true)
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.isBuildingsEnabled = true

        //val initialPosition = LatLng(51.5167, 13.404954)
        val latLng =
            LatLng(currentLocation?.latitude ?: return, currentLocation?.longitude ?: return)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19f))

        setupMapUi()

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment

        val markerOptions = MarkerOptions()
            .position(latLng)
            .title("")
            .snippet(getAdress(latLng.latitude, latLng.longitude))
            .draggable(true)
            .visible(true)
            .zIndex(0f)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        mapFragment?.getMapAsync {
            it.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition(latLng, 17F, 60F, 0F)
                )
            )
            it.addMarker(markerOptions)
        }
    }

    private fun setupMapUi() {
        // Set up the SearchView to listen for query submissions
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchLocation(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        binding.radioGroup.setOnCheckedChangeListener { _, itemId: Int ->
            when (itemId) {
                R.id.normalBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.satelliteBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.hybridBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                R.id.terrainBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        }
    }

    private fun searchLocation(query: String) {
        val geocoder = Geocoder(requireContext())
        val addressList: List<Address>? = geocoder.getFromLocationName(query, 1)
        if (!addressList.isNullOrEmpty()) {
            val address = addressList[0]
            val latLng = LatLng(address.latitude, address.longitude)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
            googleMap.addMarker(MarkerOptions().position(latLng).title(query))
        }
    }

    fun moveToCurrentLocation() {
        if (currentLocation != null) {
            val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19f))
        } else {
            fetchLocation()
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    1000
                )
            }
        }
        val task =
            fusedLocationProviderClient?.lastLocation
        task?.addOnSuccessListener { location ->
            if (location != null) {
                this.currentLocation = location
                val mapFragment =
                    childFragmentManager.findFragmentById(R.id.mapFragment)
                            as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            1000 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }

    private fun getAdress(lat: Double, lon: Double): String? {
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val adresses = geoCoder.getFromLocation(lat,lon, 1)
        return adresses?.get(0)?.getAddressLine(0).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = args.cityName

        // Beispielcode, um die Stadt auf der Karte anzuzeigen
        // Dies kann Google Maps oder ein anderes Karten-API sein
        showCityOnMap(cityName)
    }

    private fun showCityOnMap(cityName: String) {
        // Logik, um die Stadt auf der Karte anzuzeigen
        // z.B., die Karte auf die Stadt zentrieren oder einen Marker setzen
    }
}
