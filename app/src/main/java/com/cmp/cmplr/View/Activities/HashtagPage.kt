package com.cmp.cmplr.View.Activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.HashtagController
import com.cmp.cmplr.Controller.HomeController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.R
import kotlinx.coroutines.runBlocking

class HashtagPage:AppCompatActivity() {



    private var localStorage = LocalStorage()
    var hashtagController:HashtagController= HashtagController()
    lateinit var rv_showData : RecyclerView
    var postsList: ArrayList<HomePostData> = ArrayList()

    val infiniteScrollRecycler : InfiniteScrollRecycler by lazy {
        InfiniteScrollRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hashtag_page)
        val extras = intent.extras
        var hashtag_value:String?=""
        if (extras != null) {
            hashtag_value = extras.getString("hashtag")
            Log.d("hashtagLOL",hashtag_value.toString())
        }
        var token:String?=localStorage.getTokenData(this@HashtagPage)
        rv_showData=findViewById<RecyclerView>(R.id.theinfinte_hash)
        infiniteScrollRecycler.putToken(token) //passing the token to the adapter
        infiniteScrollRecycler.putActivity(this@HashtagPage as Activity)
        rv_showData.adapter=infiniteScrollRecycler



        var backendPair: ListBooleanPair
        runBlocking {

            backendPair=hashtagController.GetPostsBackend(token)

        }
        if(backendPair.getIsSucess()){

            infiniteScrollRecycler.updateList(backendPair.getList())
            infiniteScrollRecycler.notifydataSet()
        }







        var isIn=false
        var scrollView: ScrollView =findViewById(R.id.scroll_view_infinite_hash)
        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!scrollView.canScrollVertically(1) && !isIn) {
                isIn=true
                var backendPair: ListBooleanPair
                runBlocking{
                    backendPair=hashtagController.GetPostsBackend(token)
                    if(backendPair.getIsSucess()){

                        infiniteScrollRecycler.updateList(backendPair.getList())
                        infiniteScrollRecycler.notifydataSet()
                    }
                    infiniteScrollRecycler.wantMorePosts=false
                    infiniteScrollRecycler.notifydataSet()
                    scrollView.visibility= View.VISIBLE
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









/*
*
val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("hashtag")
            Log.d("hashtagLOL",value.toString())
        }
*
* */

