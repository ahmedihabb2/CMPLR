package com.cmp.cmplr.View.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cmp.cmplr.Controller.WritePostController
import com.cmp.cmplr.R
import com.cmp.cmplr.databinding.ActivityMainScreenBinding


interface WritePostRequester {
    fun onPostRequestDone(result: WritePostController.PostResult)
}

interface WritePostButtonEventHandler {
    fun onWritePostClicked(requester: WritePostRequester)
}

class MainScreenActivity : AppCompatActivity(),
                           WritePostButtonEventHandler {

    private lateinit var binding: ActivityMainScreenBinding

    private lateinit var writePostActivityLauncher : ActivityResultLauncher<Intent>
    private lateinit var writePostRequesterActivity : WritePostRequester

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.nav_fragment))

        writePostActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val res = it.data?.getIntExtra("WritePostActivityResult",0)

                writePostRequesterActivity.onPostRequestDone(WritePostController.PostResult.fromInt(res))
            }
        }
    }

    override fun onWritePostClicked(requester: WritePostRequester) {
        writePostRequesterActivity = requester
        writePostActivityLauncher.launch(Intent(this@MainScreenActivity, WritePostActivity::class.java))
    }

}