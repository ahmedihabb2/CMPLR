package com.cmp.cmplr.View.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.HomeController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R
import com.cmp.cmplr.View.Activities.LoginActivity
import com.cmp.cmplr.View.Activities.WritePostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class HomeScreenFragment:Fragment() {
    //lateinit var mainHandler: Handler



    private var localStorage = LocalStorage()
    var homeController=HomeController()



    lateinit var rv_showData :RecyclerView
    //val infiniteScrollRecycler : InfiniteScrollRecycler = InfiniteScrollRecycler()
    var postsList: ArrayList<HomePostData> = ArrayList()
    var listsize:Int=0;

    val infiniteScrollRecycler : InfiniteScrollRecycler by lazy {
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val write_btn : FloatingActionButton = view.findViewById(R.id.writePostBtn2)
        write_btn.setOnClickListener{
            val intent = Intent(activity?.applicationContext, WritePostActivity::class.java)
            startActivity(intent)
        }
        var token:String?=localStorage.getTokenData(requireActivity())
        rv_showData=requireView().findViewById<RecyclerView>(R.id.theinfinte)
        infiniteScrollRecycler.putToken(token) //passing the token to the adapter
        infiniteScrollRecycler.putActivity(activity as Activity)
        rv_showData.adapter=infiniteScrollRecycler
        var backendPair: ListBooleanPair
        runBlocking {

            backendPair=homeController.GetPostsBackend(token)

        }
        if(backendPair.getIsSucess()){

            infiniteScrollRecycler.updateList(backendPair.getList())
            infiniteScrollRecycler.notifydataSet()
        }
        var isIn=false
        var scrollView:ScrollView=requireView().findViewById(R.id.scroll_view_infinite)
        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!scrollView.canScrollVertically(1) && !isIn) {
                    isIn=true
                var backendPair: ListBooleanPair
                    runBlocking{
                        backendPair=homeController.GetPostsBackend(token)
                        if(backendPair.getIsSucess()){

                            infiniteScrollRecycler.updateList(backendPair.getList())
                            infiniteScrollRecycler.notifydataSet()
                        }
                        infiniteScrollRecycler.wantMorePosts=false
                        infiniteScrollRecycler.notifydataSet()
                        scrollView.visibility=View.VISIBLE
                        isIn=false
                    }
//                    if(backendPair.getIsSucess()){
//
//                        infiniteScrollRecycler.updateList(backendPair.getList())
//                        infiniteScrollRecycler.notifydataSet()
//                    }
//                    infiniteScrollRecycler.wantMorePosts=false
//                    infiniteScrollRecycler.notifydataSet()
//                    scrollView.visibility=View.VISIBLE
            }
        }

    }



}
