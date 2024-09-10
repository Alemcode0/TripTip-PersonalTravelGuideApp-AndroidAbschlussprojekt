package com.example.abschlissprojekt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.abschlissprojekt.databinding.ActivityMainBinding
import com.example.abschlissprojekt.ui.MapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteDatabase("destination_database")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController

        // FloatingActionButton initialisieren
        val fab: FloatingActionButton = binding.floatingActionButton

//        fab.setOnClickListener {
//            handleFabClick()
//        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            //den FAB je nach Fragment ein-/auszublenden
            when (destination.id) {
                R.id.homeFragment, R.id.favouriteFragment, R.id.mapFragment -> fab.show()
                else -> fab.hide()
        }
    }
        binding.bottomNavigationView.setupWithNavController(navHost.navController)

        binding.floatingActionButton.setOnClickListener {
            navHost.findNavController().navigate(R.id.profileFragment)

//            //der Standort aktualisieren
//            val bundle = Bundle().apply { putBoolean("shouldUpdateLocation", true) }
//            navHost.findNavController().navigate(R.id.mapFragment, bundle)
        }

        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    binding.fragmentContainerView.findNavController().navigateUp()
                }
            }
        )
    }

//    private fun handleFabClick() {
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
//
//        if (currentFragment is MapFragment) {
//            currentFragment.moveToCurrentLocation()
//        } else {
//            //Navigate zum MapFragment und dann zum aktuellen Standort
//            navController.navigate(R.id.mapFragment)
//            // die Kamera nach dem Wechsel zum Fragment bewegen:
//            navController.addOnDestinationChangedListener { _, destination, _ ->
//                if (destination.id == R.id.mapFragment) {
//                    val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? MapFragment
//                    fragment?.moveToCurrentLocation()
//                }
//            }
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.fragmentContainerView.findNavController().navigateUp() || super.onSupportNavigateUp()
    }
}
