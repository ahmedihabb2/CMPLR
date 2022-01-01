package com.cmp.cmplr.Adapter

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.API.Api_Instance
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.R
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStream
import java.net.URL

//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread

/**
 * This class is responsible for beign the adapter for any view having infinte posts
 *  - On click listeners for each post
 *  - Setting the content of each post
 *  - Making the backend calls of likes and following
 */

class InfiniteScrollRecycler : RecyclerView.Adapter<InfiniteScrollRecycler.InfiniteViewHolder>() {

    val tag = "kak"
    var token: String? = ""
    var blogName:String?=""
    var postList: ArrayList<HomePostData> = ArrayList()
    lateinit var myActivity: Activity

    fun putActivity(activity: Activity) {
        myActivity = activity
    }


    fun putToken(token_passed: String?) {
        token = token_passed
        //homeModel.putToken(token)
    }
    fun putBlogName(blognamePassed:String?){
        blogName=blognamePassed
    }


    fun notifydataSet() {
        notifyDataSetChanged()
    }

    fun updateList(posttList: ArrayList<HomePostData>) {
        this.postList += posttList


    }

    /**
     *  Inner class for setting the content of each post
     *
     *  @param itemView
     *
     */

    class InfiniteViewHolder(itemView: View,blogName_user:String?) : RecyclerView.ViewHolder(itemView) {
        var usernameBlog=blogName_user
        //var progressBar:ProgressBar=itemView.findViewById(R.id.progressBar_home)
        var usr_img: ImageView = itemView.findViewById(R.id.user_pic)
        var usr_name: TextView = itemView.findViewById(R.id.username_home)
        var comments: TextView = itemView.findViewById(R.id.comments_btn)
        var html_post: WebView = itemView.findViewById(R.id.html_view_instance)
        var first_hashtag: TextView = itemView.findViewById(R.id.first_hashtag)
        var second_hashtag: TextView = itemView.findViewById(R.id.second_hashtag)
        var notes_btn: TextView = itemView.findViewById(R.id.comments_btn)
        var love_btn: ImageView = itemView.findViewById(R.id.love_btn)

        var follow_text: TextView = itemView.findViewById(R.id.follow_btn)

        /**
         *  function to bind each post
         *  @param homepost each post content and information
         *
         *
         */


        fun bind(homepost: HomePostData) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var html: String = homepost.post.content


            html_post.loadData(html, "text/html", "UTF-8");
            //html_post.loadData(temphtml, "text/html", "base64")
            usr_name.text = (homepost.blog.blog_name).toString()
            comments.text = (homepost.post.notes_count).toString() + " notes"
            first_hashtag.text = ""
            second_hashtag.text = ""
            if(!homepost.post.tags.isNullOrEmpty()) {
                if (homepost.post.tags.size == 1 && homepost.post.tags[0] != "") {

                    first_hashtag.text = "#" + homepost.post.tags[0]
                } else if (homepost.post.tags.size > 1) {
                    first_hashtag.text = "#" + homepost.post.tags[0].replace("\"", "")
                    second_hashtag.text = "#" + homepost.post.tags[1].replace("\"", "")

                }
            }



            var inputStream: InputStream? = null
            var bitmap: Bitmap? = null
            //var URL:String="https://assets.tumblr.com//images//default_avatar//cone_closed_128.png"
            var URL: String = homepost.blog.avatar
            try {
                inputStream = URL(URL).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                usr_img.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                usr_img.setImageResource(R.drawable.kil)
            }
            if (homepost.post.is_liked) {
                love_btn.setImageResource(R.drawable.red_heart)
            } else {
                love_btn.setImageResource(R.drawable.heart_vector)
            }

            if(usernameBlog==homepost.blog.blog_name){
                follow_text.text = ""
            } else{
            if (homepost.blog.follower == true ) {
                follow_text.text = "Unfollow"
                follow_text.setTextColor(Color.parseColor("#808080"))
            } else {
                follow_text.text = "Follow"
                follow_text.setTextColor(Color.parseColor("#00B8FF"))

            }
            }


        }

    }


    /**
     *  member function to inflate the views
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.whole_post_view, parent, false)
        return InfiniteViewHolder(view,blogName)
    }

    /**
     *  member function containing the view holder to make its onclick listeners
     *
     *  @param holder the view holder of the post
     *  @param position the position of the post
     */
    override fun onBindViewHolder(holder: InfiniteViewHolder, position: Int) {
        var post: HomePostData = postList.get(position)
        holder.bind(post)

        //////////////////////////////////////////////////////////////////////////////
        //////////////////////////////****ON CLICK LISTENERS****//////////////////////
        //////////////////////////////////////////////////////////////////////////////
        holder.follow_text.setOnClickListener {
            val follow_text = it as TextView
            var text_inside=follow_text.text.toString()
            if(text_inside!=""){
                if (post.blog.follower == true  ) {  //unfollow
                    runBlocking {
                        try {
                            val response =
                                Api_Instance.api.unfollowBlog("Bearer $token", post.blog.blog_name)
                            if ((response).isSuccessful) {
                                follow_text.text = "Follow"
                                follow_text.setTextColor(Color.parseColor("#00B8FF"))
                                post.blog.follower = false

                            } else {
                            }
                        } catch (e: HttpException) {
                        }
                    }
                } else {         //follow
                   runBlocking {
                        try {
                            val response =
                                Api_Instance.api.followBlog("Bearer $token", post.blog.blog_name)
                            if ((response).isSuccessful) {
                                follow_text.text = "UnFollow"
                                follow_text.setTextColor(Color.parseColor("#808080"))
                                post.blog.follower = true

                            } else {
                            }
                        } catch (e: HttpException) {
                        }
                    }

                }
            }

        }

        holder.love_btn.setOnClickListener {
            val lovbtn = it as ImageView
            if (post.post.is_liked == true) {  //unlike the post

                runBlocking {
                    try {
                        val response =
                            Api_Instance.api.unlikePost("Bearer $token", post.post.post_id)
                        if ((response).isSuccessful) {
                            lovbtn.setImageResource(R.drawable.heart_vector)
                            post.post.is_liked = false
                        } else {
                        }

                    } catch (e: HttpException) {
                    }
                }


            } else {   //like the post
                runBlocking {
                    try {
                        val response = Api_Instance.api.likePost("Bearer $token", post.post.post_id)
                        if ((response).isSuccessful) {
                            lovbtn.setImageResource(R.drawable.red_heart)
                            post.post.is_liked = true
                        } else {
                        }

                    } catch (e: HttpException) {
                    }

                }
            }
        }

        holder.first_hashtag.setOnClickListener {
            val hashtagtextView = it as TextView
            if (hashtagtextView.text.toString() != "") {
                var temp: String =
                    "pressed on image of postition:" + position.toString() + " ,array size=" + postList.size.toString()
//                var i = Intent(myActivity.applicationContext, HashtagPage::class.java)
//                i.putExtra("hashtag", hashtagtextView.text.toString().replace("#", ""))
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(myActivity.applicationContext, i, null)
                var data = Bundle()
                data.putString("hashtag_value", hashtagtextView.text.toString().replace("#", ""))
                it.findNavController().navigate(R.id.hashtagPage, data)
            }

        }
        holder.second_hashtag.setOnClickListener {
            //var tempp:TextView=(TextView)it
            val hashtagtextView = it as TextView
            if (hashtagtextView.text.toString() != "") {
//                var temp: String =
//                    "pressed on image of postition:" + position.toString() + " ,array size=" + postList.size.toString()
//                var i = Intent(myActivity.applicationContext, HashtagPage::class.java)
//                i.putExtra("hashtag", hashtagtextView.text.toString().replace("#", ""))
//                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(myActivity.applicationContext, i, null)
                var data = Bundle()
                data.putString("hashtag_value", hashtagtextView.text.toString().replace("#", ""))
                it.findNavController().navigate(R.id.hashtagPage, data)
            }

        }


        holder.usr_img.setOnClickListener {
            var blog_data = Bundle()
            blog_data.putString("blog_name", post.blog.blog_name)
            blog_data.putString("blog_avatar", post.blog.avatar)
            blog_data.putInt("blog_id", post.blog.blog_id)
            it.findNavController().navigate(R.id.action_global_blogFragment, blog_data)
        }
        holder.usr_name.setOnClickListener {
            var blog_data = Bundle()
            blog_data.putString("blog_name", post.blog.blog_name)
            blog_data.putString("blog_avatar", post.blog.avatar)
            blog_data.putInt("blog_id", post.blog.blog_id)
            it.findNavController().navigate(R.id.action_global_blogFragment, blog_data)
        }
        holder.notes_btn.setOnClickListener {
            var data = Bundle()
            data.putInt("post_id", post.post.post_id)
            it.findNavController().navigate(R.id.action_global_notesFragment, data)
        }
    }

    /**
     * member to get the item count
     * @return the list of posts size
     */

    override fun getItemCount(): Int {
        return postList.size
    }
}


