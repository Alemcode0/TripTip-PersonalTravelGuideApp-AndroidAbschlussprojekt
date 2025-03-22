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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.favouriteFragment, R.id.mapFragment -> fab.show()
                else -> fab.hide()
        }
    }
        binding.bottomNavigationView.setupWithNavController(navHost.navController)

        binding.floatingActionButton.setOnClickListener {
            navHost.findNavController().navigate(R.id.profileFragment)
        }

        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    binding.fragmentContainerView.findNavController().navigateUp()
                }
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.fragmentContainerView.findNavController().navigateUp() || super.onSupportNavigateUp()
    }
}
