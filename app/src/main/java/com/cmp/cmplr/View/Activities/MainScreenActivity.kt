package com.cmp.cmplr.View.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.R
import com.cmp.cmplr.databinding.ActivityMainScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


interface WritePostRequester {
    fun onPostRequestDone(result: WritePostController.PostResult)
}

interface WritePostButtonEventHandler {
    fun onWritePostClicked(requester: WritePostRequester)
    fun userID(): Int
}

class MainScreenActivity : AppCompatActivity(),
    WritePostButtonEventHandler {
    lateinit var binding: ActivityMainScreenBinding
    private var userID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navController = this.findNavController(R.id.nav_fragment)
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.notesFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }
        userID = intent.getIntExtra(getString(R.string.user_ID_Intent_search_key), 0)
        //This really should never happen
        if (userID < 0) {
            Toast.makeText(
                applicationContext,
                getString(R.string.errorMsgIfNoValidUserID),
                Toast.LENGTH_LONG
            ).show()
            finish()
        }

        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.nav_fragment))
    }

    override fun onWritePostClicked(requester: WritePostRequester) {
        val i = Intent(this@MainScreenActivity, WritePostActivity::class.java)
        i.putExtra(getString(R.string.user_ID_Intent_search_key), userID)
        startActivity(i)

        //TODO: remove this hardcoding of result later
        requester.onPostRequestDone(WritePostController.PostResult.SUCCESS)
    }

    override fun userID() = userID
}