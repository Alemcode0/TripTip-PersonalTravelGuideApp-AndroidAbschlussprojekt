package com.example.abschlissprojekt.ui


import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
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
    private var currentLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(it)
        }!!
        fetchLocation()

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
//
//        val locationPermissionRequest = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { permissions ->
//            when {
//                permissions.getOrDefault(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    false
//                ) || permissions.getOrDefault(
//                    Manifest.permission.ACCESS_COARSE_LOCATION, false
//                ) -> {
//                    Toast.makeText(
//                        requireContext(), "Location access granted", Toast.LENGTH_SHORT
//                    ).show()
//
//                    if (LocationManagerCompat.isLocationEnabled(requireContext().getSystemService(
//                            LOCATION_SERVICE) as LocationManager)) {
//                        if (ActivityCompat.checkSelfPermission(
//                                requireContext(),
//                                Manifest.permission.ACCESS_FINE_LOCATION
//                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                                requireContext(),
//                                Manifest.permission.ACCESS_COARSE_LOCATION
//                            ) != PackageManager.PERMISSION_GRANTED
//                        ) {
//                            return@registerForActivityResult
//                        }
//                        fusedLocationProviderClient.getCurrentLocation(
//                            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
//                            CancellationTokenSource().token
//                        ).addOnCompleteListener {
//                            val location = "Latitude: ${it.result.latitude}\nLongitude: ${it.result.longitude}"
//                            //binding.textView.text = location // falls TextView vorhanden ist
//                        }
//                    } else {
//                        Toast.makeText(
//                            requireContext(), "Please turn ON the location.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        createLocationRequest()
//                    }
//                }
//                else -> {
//                    Toast.makeText(requireContext(), "No location access", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//        binding.floatingActionBtn.setOnClickListener {
//            locationPermissionRequest.launch(
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//            )
//        }
//
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }
//
//    private fun createLocationRequest() {
//        val locationRequest = LocationRequest.Builder(
//            Priority.PRIORITY_HIGH_ACCURACY, 10000
//        ).setMinUpdateIntervalMillis(5000).build()
//
//        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
//
//        val client = LocationServices.getSettingsClient(requireContext())
//        val task = client.checkLocationSettings(builder.build())
//
//        task.addOnSuccessListener{
//
//        }
//
//        task.addOnFailureListener { e ->
//            if (e is ResolvableApiException) {
//                try {
//
//                    e.startResolutionForResult(requireActivity(), 100)
//                } catch (sendEx: java.lang.Exception) {
//
//                }
//            }
//        }
//    }
//    private fun isLocationEnabled(): Boolean {
//        val locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
//
//        try {
//            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    return false
//    }


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

        //googleMap.addMarker(markerOptions)

        binding.radioGroup.setOnCheckedChangeListener { _, itemId: Int ->
            when (itemId) {
                R.id.normalBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                R.id.satelliteBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                R.id.hybridBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                R.id.terrainBtn -> googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
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

    }
}