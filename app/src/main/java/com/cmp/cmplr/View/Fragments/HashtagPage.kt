package com.cmp.cmplr.View.Fragments

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.HashtagController
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.R
import kotlinx.coroutines.runBlocking

class HashtagPage : Fragment() {


    private var localStorage = LocalStorage()
    var hashtagController: HashtagController = HashtagController()
    lateinit var rv_showData: RecyclerView
    var postsList: ArrayList<HomePostData> = ArrayList()

    val infiniteScrollRecycler: InfiniteScrollRecycler by lazy {
        InfiniteScrollRecycler()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("kak2", "view made")

        return inflater.inflate(R.layout.hashtag_page, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.hashtag_page)
        //val extras = intent.extras
        var hashtag_value: String? = ""
        hashtag_value= arguments?.get("hashtag_value") as String

//        if (extras != null) {
//            hashtag_value = extras.getString("hashtag")
//            Log.d("hashtagLOL", hashtag_value.toString())
//        }
        var token: String? = localStorage.getTokenData(requireActivity())

        val hashtag_toolbar: Toolbar = requireView().findViewById(R.id.toolbar_hashtag)
        //ResourcesCompat.getColor(getResources(), R.color.white, null)
        hashtag_toolbar.title = "#" + hashtag_value
        hashtag_toolbar.setTitleTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
        rv_showData = requireView().findViewById<RecyclerView>(R.id.theinfinte_hash)
        var blogName:String?=localStorage.getBlogName(requireActivity())
        infiniteScrollRecycler.putBlogName(blogName)
        infiniteScrollRecycler.putToken(token) //passing the token to the adapter
        //infiniteScrollRecycler.putActivity(this@HashtagPage as Activity)
        rv_showData.adapter = infiniteScrollRecycler


        var backendPair: ListBooleanPair
        runBlocking {

            backendPair = hashtagController.GetPostsBackend(hashtag_value, token)
            Log.d("hashtag", "finsihed request")
        }
        if (backendPair.getIsSucess()) {

            Log.d("hashtag", "req sucess")
            infiniteScrollRecycler.updateList(backendPair.getList())
            infiniteScrollRecycler.notifydataSet()
        }


        var isIn = false
        var scrollView: ScrollView = requireView().findViewById(R.id.scroll_view_infinite_hash)
        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!scrollView.canScrollVertically(1) && !isIn) {
                isIn = true
                var backendPair: ListBooleanPair
                runBlocking {
                    Log.d("hashtag", "Getting more postsssssss")
                    backendPair = hashtagController.GetPostsBackend(hashtag_value, token)
                    if (backendPair.getIsSucess()) {

                        infiniteScrollRecycler.updateList(backendPair.getList())
                        infiniteScrollRecycler.notifydataSet()
                    }
                    infiniteScrollRecycler.notifydataSet()
                    scrollView.visibility = View.VISIBLE
                    isIn = false
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

