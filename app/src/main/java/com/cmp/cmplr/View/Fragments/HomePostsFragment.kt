package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R

class HomePostsFragment:Fragment() {

    lateinit var rv_showData :RecyclerView
    val infiniteScrollRecycler : InfiniteScrollRecycler by lazy {
        InfiniteScrollRecycler()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.infinite_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postsList: ArrayList<UserPost> = ArrayList()
        for (i in 1 ..5){
            var post:UserPost= UserPost("kil",(R.drawable.kil))
            postsList.add(post)
        }
        val user_pic : ImageView =view.findViewById(R.id.user_pic)
        val user_name: TextView = view.findViewById(R.id.username_home)

        rv_showData=requireView().findViewById(R.id.infinite_posts)
        rv_showData.adapter=infiniteScrollRecycler
        infiniteScrollRecycler.setList(postsList)


    }



}
