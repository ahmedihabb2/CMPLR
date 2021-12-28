package com.cmp.cmplr.View.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cmp.cmplr.R

class HashtagPage:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("hashtag")
            Log.d("hashtagLOL",value.toString())
        }
    }
}