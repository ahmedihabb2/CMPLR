package com.cmp.cmplr.View.Fragments

import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.HomeController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.WritePostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runBlocking

class HomeScreenFragment:Fragment() {
    //lateinit var mainHandler: Handler



    private var localStorage = LocalStorage()
    var homeController=HomeController()



    lateinit var rv_showData :RecyclerView
    //val infiniteScrollRecycler : InfiniteScrollRecycler = InfiniteScrollRecycler()
    var postsList: ArrayList<HomePostData> = ArrayList()
    var listsize:Int=0;

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
        val write_btn : FloatingActionButton = view.findViewById(R.id.writePostBtn2)
        write_btn.setOnClickListener {
            val intent = Intent(activity?.applicationContext, WritePostActivity::class.java)
            startActivity(intent)
        }
        super.onViewCreated(view, savedInstanceState)
        Log.d("kak2","after super made")
        var token:String?=localStorage.getTokenData(requireActivity())

        Log.d("kak2","after array made")


        Log.d("kak2","before adapter setting made")

        rv_showData=requireView().findViewById<RecyclerView>(R.id.theinfinte)

        Log.d("kak2","line1")

        infiniteScrollRecycler.putToken(token) //passing the token to the adapter
        infiniteScrollRecycler.putActivity(activity as Activity)
        rv_showData.adapter=infiniteScrollRecycler

        Log.d("kak2","line2")


        Log.d("kak2","after adapter setting made")

        var progressBar:ProgressBar=requireView().findViewById(R.id.progressBar_home)

        //var recyclerView=requireView().findViewById(R.id.theinfinte)
        var backendPair: ListBooleanPair

        runBlocking {

            progressBar.visibility=View.VISIBLE
            backendPair=homeController.GetPostsBackend(token)
            progressBar.visibility=View.GONE

        }
        if(backendPair.getIsSucess()){

            infiniteScrollRecycler.updateList(backendPair.getList())
            infiniteScrollRecycler.notifydataSet()
        }

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {

                Log.d("kak2,","Interrupt nowwwwwwwwwww")
                    rv_showData.visibility=View.INVISIBLE
                    progressBar.visibility=View.VISIBLE
                if(infiniteScrollRecycler.wantMorePosts==true) {

                    var backendPair: ListBooleanPair

                    runBlocking {
                        Log.d("blocking","here")
                        backendPair=homeController.GetPostsBackend(token)
                        Log.d("blocking","after here")

                    }
                    if(backendPair.getIsSucess()){

                        infiniteScrollRecycler.updateList(backendPair.getList())
                        infiniteScrollRecycler.notifydataSet()
                    }
                    infiniteScrollRecycler.wantMorePosts=false
                    infiniteScrollRecycler.notifydataSet()
                }
                    progressBar.visibility=View.GONE
                    rv_showData.visibility=View.VISIBLE
                mainHandler.postDelayed(this, 10000)
            }
        })




    }



}
