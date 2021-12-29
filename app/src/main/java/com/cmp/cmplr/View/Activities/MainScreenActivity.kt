package com.cmp.cmplr.View.Activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cmp.cmplr.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navController = this.findNavController(R.id.nav_fragment)
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.homeScreenFragment || nd.id == R.id.searchScreenFragment || nd.id == R.id.messagesScreenFragment || nd.id == R.id.profileScreenFragment) {
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setupWithNavController(
            findNavController(R.id.nav_fragment)
        )
    }


}