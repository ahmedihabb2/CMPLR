package com.cmp.cmplr.View.Activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R

class InfinitePostActivity : AppCompatActivity() {

    lateinit var infiniteView: RecyclerView
    lateinit var usr_pic: ImageView
    lateinit var usr_name: TextView
    var postList: ArrayList<UserPost> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infinite_posts)

        infiniteView = findViewById(R.id.infinite_posts)
        usr_pic = findViewById(R.id.user_pic)
        usr_name = findViewById(R.id.username_home)


    }
}