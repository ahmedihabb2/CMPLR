package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R

class HomeScreenFragment:Fragment() {
    lateinit var rv_showData :RecyclerView
    //val infiniteScrollRecycler : InfiniteScrollRecycler = InfiniteScrollRecycler()
    var postsList: ArrayList<UserPost> = ArrayList()

    val infiniteScrollRecycler : InfiniteScrollRecycler by lazy {
      Log.d("kak2","lazy eval")
       InfiniteScrollRecycler()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("kak2","view made")

        return inflater.inflate(R.layout.infinite_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("kak2","before super made")

        super.onViewCreated(view, savedInstanceState)
        Log.d("kak2","after super made")

        for (i in 1 ..5){
            var post:UserPost= UserPost("kil",(R.drawable.kil))
            postsList.add(post)
        }
        Log.d("kak2","after array made")

        /*val user_pic : ImageView =view.findViewById(R.id.user_pic)
        val user_name: TextView = view.findViewById(R.id.username_home)
        val share_btn:ImageView=view.findViewById(R.id.share_btn)
        val reblog_btn:ImageView=view.findViewById(R.id.reblog_btn)
        val love_btn:ImageView=view.findViewById(R.id.love_btn)*/
        Log.d("kak2","before adapter setting made")

        rv_showData=requireView().findViewById<RecyclerView>(R.id.theinfinte)

        Log.d("kak2","line1")

        rv_showData.adapter=infiniteScrollRecycler
        Log.d("kak2","line2")

        infiniteScrollRecycler.setList(postsList)
        Log.d("kak2","after adapter setting made")
        //Log.d("kak2",infiniteScrollRecycler.postList[0].name.toString())
        Log.d("kak2", (rv_showData.adapter as InfiniteScrollRecycler).postList[0].name.toString())

/*
        user_pic.setOnClickListener{
            Toast.makeText( context, "user picture", Toast.LENGTH_SHORT).show()
        }
*/


    }



}
